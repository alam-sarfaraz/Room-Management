package com.inn.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inn.customException.CustomException;
import com.inn.dao.JasperReportWrapperDao;
import com.inn.model.PurchaseItem;
import com.inn.roomUtils.RoomUtils;
import com.inn.service.ExportAllDataIntoExcelFileService;
import com.inn.wrapper.JasperReportWrapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExportAllDataIntoExcelFileServiceImpl implements ExportAllDataIntoExcelFileService {

  @Autowired
  JasperReportWrapperDao jasperReportWrapperDao;

  @Override
  public ResponseEntity<byte[]> ExportDataToExcelFile() throws Exception {
    try {
      log.info("Inside the ExportDataToExcelFile ");
      String downloadPath = "C:\\Room-Management\\DownloadExportExcelSheet\\";
      File file = new File(downloadPath);
      if (!file.exists()) {
        file.mkdirs();
      }
      log.info("DownloadPath for export file :{}", downloadPath);
      String absolutePath = new ClassPathResource("static/ExportFile").getFile().getAbsolutePath();
      String path = absolutePath + "\\ALL_DATA.xlsx";
      log.info("absolutePath for export file :{}", path);
      FileInputStream fileInputStream = new FileInputStream(path);
      SXSSFWorkbook workbook = null;
      XSSFWorkbook wbTemplate = new XSSFWorkbook(fileInputStream);
      String fileName = "ExportData.xlsx";
      workbook = new SXSSFWorkbook(wbTemplate);
      SXSSFSheet workSheet = workbook.getSheetAt(0);
      List<JasperReportWrapper> items = jasperReportWrapperDao.findAll();
      Integer rowIndex = 1;
      for (JasperReportWrapper item : items) {
        rowIndex = populateWorkSheetCellData(workSheet, rowIndex, item);
      }
      FileOutputStream outputStream = new FileOutputStream(new File(downloadPath + fileName));
      workbook.write(outputStream);
      outputStream.close();
      return RoomUtils.downloadFile(path);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  private Integer populateWorkSheetCellData(SXSSFSheet workSheet, Integer rowIndex, JasperReportWrapper item) {
    Row row = workSheet.getRow(rowIndex);
    if (row == null) {
      row = workSheet.createRow(rowIndex);
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    List<String> cellValueList = new ArrayList<>();
    cellValueList.add(item.getId().toString());
    cellValueList.add(sdf.format(item.getDate()));
    cellValueList.add(item.getTime());
    cellValueList.add(item.getUserName());
    cellValueList.add(item.getItemName());
    cellValueList.add(item.getQuantity().toString());
    cellValueList.add(item.getPrice().toString());
    paymentCellRender(cellValueList, row);
    return ++rowIndex;
  }

  private void paymentCellRender(List<String> cellValueList, Row row) {
    Integer columnIndex = 0;
    for (String cellResult : cellValueList) {
      Cell cell = row.createCell(columnIndex++);
      cell.setCellValue(cellResult);
    }

  }

  @Override
  public String ExportExcelFileDataToDB(MultipartFile file) throws Exception {
    try {
      log.info("Inside the ExportExcelFileDataToDB :{}", file.getOriginalFilename());
      if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
        throw new CustomException("Invalid file upload only upload xlsx file !!!!!");
      }
      PurchaseItem purchaseItem = new PurchaseItem();
      InputStream inputStream = file.getInputStream();
      Workbook wb = WorkbookFactory.create(inputStream);
      Sheet sheet = wb.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();
      while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        if (row.getRowNum() == 0) {
          log.info("Ignore ----");
        } else {
          Iterator<Cell> cellIterator = row.cellIterator();
          while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {
              case Cell.CELL_TYPE_STRING:
                purchaseItem.setUserName(cell.getStringCellValue());
                System.out.print(cell.getStringCellValue() + "----");
                break;
            }
          }
        }
        System.out.println("");
      }
      inputStream.close();
      return "Data Insert successfully";
    } catch (Exception e) {
      log.error("Error occured due to :", e.getMessage());
      throw e;
    }

  }
}
