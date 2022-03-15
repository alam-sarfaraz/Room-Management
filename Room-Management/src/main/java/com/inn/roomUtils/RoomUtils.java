package com.inn.roomUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inn.customException.CustomException;
import com.inn.model.Items;
import com.inn.roomConstants.RoomConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomUtils {

  public static String dateFormatter() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(RoomConstants.DATE_PATTERN);
    String date = simpleDateFormat.format(new java.util.Date());
    return date;
  }

  public static String timeFormatter() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(RoomConstants.TIME_PATTERN);
    String time = simpleDateFormat.format(new java.util.Date());
    return time;
  }

  public static String modifiedDateTimeFormatter() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(RoomConstants.MODIFIED_DATE_TIME);
    String date = simpleDateFormat.format(new java.util.Date());
    return date;
  }

  public static Date dateFormatForDbDate() {
    long currentTimeMillis = System.currentTimeMillis();
    Date date = new Date(currentTimeMillis);
    return date;
  }

  public static String generateUniqueUser(String firstName) {
    String userName = firstName + "-" + UUID.randomUUID().toString().substring(1, 5).toUpperCase();
    return userName;
  }

  public static void validatePasswordAndConfirmPassword(String password, String confirmpassword)
      throws CustomException {
    if (!password.equals(confirmpassword)) {
      throw new CustomException("Password and Confirm password are not same");
    }
  }

  public static void mobileNumberValidate(String mobileNumber) throws CustomException {
    String strPattern = "^[0-9]{10}$";
    if (!mobileNumber.matches(strPattern)) {
      throw new CustomException("Invalid Mobile number....");
    }
  }

  public static void genderValidate(String gender) throws CustomException {
    if (!gender.equalsIgnoreCase("MALE") && !gender.equalsIgnoreCase("FEMALE") && !gender.equalsIgnoreCase("OTHER")) {
      throw new CustomException("Invalid gender value");
    }
  }

  public static ResponseEntity<byte[]> downloadFile(String filePath) {
    log.info("Inside the downloadFile  :{}", filePath);
    try {
      File file = new File(filePath);
      String fileName = file.getName();
      log.info("downloadFile fileName : {}", fileName);
      byte[] fileByteArray = org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(file));
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
      return new ResponseEntity<>(fileByteArray, headers, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Error inside downloadFile : {}", e.getMessage());
      return null;
    }
  }

  public static String writeDataToExcelFile(List<Items> findAllData, String userName) throws IOException {
    String basePath = "C:\\Room-Management\\DownloadExcelFile\\";
    File createFolder = new File(basePath);
    if (!createFolder.exists()) {
      createFolder.mkdirs();
    }
    String ExcelFileName = "ALL_RECORD";
    if (userName != null) {
      ExcelFileName = userName;
    }

    String path = basePath + ExcelFileName + ".xlsx";
    File xlsxFile = new File(path);
    if (!xlsxFile.exists()) {
      xlsxFile.createNewFile();
    }
    log.info("path :{}", path);
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("ALL_DATA");
    sheet.setColumnWidth(0, 1500);
    sheet.setColumnWidth(1, 7200);
    sheet.setColumnWidth(2, 7200);
    sheet.setColumnWidth(3, 7200);
    sheet.setColumnWidth(4, 7200);
    sheet.setColumnWidth(5, 7200);
    sheet.setColumnWidth(6, 7200);
    Font font = workbook.createFont();
    font.setFontHeightInPoints((short) 13);
    font.setFontName("Arial");
    font.setColor(IndexedColors.GREEN.getIndex());
    font.setBold(true);
    CellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    int lastRowNum = sheet.getLastRowNum();
    log.info("Value of lastRowNum :{}", lastRowNum);
    if (lastRowNum == 0) {
      XSSFRow createRow = sheet.createRow(lastRowNum);
      XSSFCell createCell = createRow.createCell(lastRowNum);
      createCell.setCellStyle(cellStyle);
      createCell.setCellValue("Id");
      XSSFCell createCell1 = createRow.createCell(1);
      createCell1.setCellStyle(cellStyle);
      createCell1.setCellValue("DATE");
      XSSFCell createCell2 = createRow.createCell(2);
      createCell2.setCellStyle(cellStyle);
      createCell2.setCellValue("TIME");
      XSSFCell createCell3 = createRow.createCell(3);
      createCell3.setCellStyle(cellStyle);
      createCell3.setCellValue("USERNAME");
      XSSFCell createCell4 = createRow.createCell(4);
      createCell4.setCellStyle(cellStyle);
      createCell4.setCellValue("ITEM_NAME");
      XSSFCell createCell5 = createRow.createCell(5);
      createCell5.setCellStyle(cellStyle);
      createCell5.setCellValue("QUANTITY");
      XSSFCell createCell6 = createRow.createCell(6);
      createCell6.setCellStyle(cellStyle);
      createCell6.setCellValue("PRICE");
    }
    Integer rowIndex = 1;
    for (Items items : findAllData) {
      rowIndex = populateWorkSheetCellData(sheet, rowIndex, items, workbook);
    }
    FileOutputStream outputStream = new FileOutputStream(path, true);
    workbook.write(outputStream);
    outputStream.close();
    return path;
  }

  private static Integer populateWorkSheetCellData(XSSFSheet sheet, Integer rowIndex, Items item,
      XSSFWorkbook workbook) {
    Row row = sheet.getRow(rowIndex);
    if (row == null) {
      row = sheet.createRow(rowIndex);
    }
    List<String> cellValueList = new ArrayList<>();
    cellValueList.add(item.getId().toString());
    cellValueList.add(item.getDate().toString());
    cellValueList.add(item.getTime());
    cellValueList.add(item.getUserName());
    cellValueList.add(item.getItemName());
    cellValueList.add(item.getQuantity().toString());
    cellValueList.add(item.getPrice().toString());
    paymentCellRender(cellValueList, row, workbook);
    return ++rowIndex;
  }

  private static void paymentCellRender(List<String> cellValueList, Row row, XSSFWorkbook workbook) {
    Font font = workbook.createFont();
    font.setFontHeightInPoints((short) 11);
    font.setFontName("Courier New");
    font.setColor(IndexedColors.WHITE.getIndex());
    font.setBold(true);
    CellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    Integer columnIndex = 0;
    for (String cellResult : cellValueList) {
      Cell cell = row.createCell(columnIndex++);
      cell.setCellStyle(cellStyle);
      cell.setCellValue(cellResult);
    }

  }
}
