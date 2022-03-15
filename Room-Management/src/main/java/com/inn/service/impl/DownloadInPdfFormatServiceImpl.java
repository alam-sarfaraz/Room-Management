package com.inn.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.inn.customException.CustomException;
import com.inn.dao.ItemsDao;
import com.inn.dao.JasperReportWrapperDao;
import com.inn.model.Items;
import com.inn.service.DownloadInPdfFormatService;
import com.inn.wrapper.JasperReportWrapper;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Slf4j
public class DownloadInPdfFormatServiceImpl implements DownloadInPdfFormatService {

  @Autowired
  JasperReportWrapperDao jasperReportWrapperDao;

  @Autowired
  ItemsDao itemDao;

  @Override
  public ResponseEntity<byte[]> downloadPdf() throws Exception {
    try {
      log.info("Inside the downloadPdf ");
      List<JasperReportWrapper> items = jasperReportWrapperDao.findAll();
      log.info("Size of Items : {}", items.size());
      if (items == null || items.isEmpty()) {
        throw new CustomException("No Data Preset");
      }

      // Create a folder
      String path = "C:\\Room-Management\\DownloadPdf\\";
      File createFolder = new File(path);
      if (!createFolder.exists()) {
        createFolder.mkdirs();
      }
      // Load japserFile
      File file = ResourceUtils.getFile("classpath:Room_Mgmt.jrxml");
      log.info("Path of  Room_Mgmt.jrxml: {}", file);
      JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
      JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
      Map<String, Object> parameter = new HashMap<>();
      parameter.put("CreatedBy", "Self");
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
      JasperExportManager.exportReportToPdfFile(jasperPrint, path + "sarfaraz.pdf");
      byte[] exportReportToPdf = JasperExportManager.exportReportToPdf(jasperPrint);
      HttpHeaders headers = new HttpHeaders();
      headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=invoice.pdf");
      return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(exportReportToPdf);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String downloadHtml() throws Exception {
    try {
      log.info("Inside the downloadHtml ");
      List<JasperReportWrapper> items = jasperReportWrapperDao.findAll();
      if (items == null || items.isEmpty()) {
        throw new CustomException("No Data Preset");
      }
      // Create a folder
      String path = "C:\\Room-Management\\DownloadHtml\\";
      File createFolder = new File(path);
      if (!createFolder.exists()) {
        createFolder.mkdirs();
      }
      // Load japserFile
      JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
      File file = ResourceUtils.getFile("classpath:Room_Mgmt.jrxml");
      log.info("Path of  Room_Mgmt.jrxml: {}", file);
      JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
      Map<String, Object> parameter = new HashMap<>();
      parameter.put("CreatedBy", "Self");
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
      JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "sarfaraz.html");
      return "File Download to this location :" + path;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public ResponseEntity<byte[]> downloadPdfFileByUsername(String username) throws Exception {
    try {
      log.info("Inside the downloadPdfFileByUsername :{}", username);
      List<Items> items = itemDao.findByUserName(username);
      if (items == null || items.isEmpty()) {
        throw new CustomException("No Data present ..........");
      }
      String path = "C:\\Room-Management\\DownloadPdfByUsername\\";
      File createFolder = new File(path);
      if (!createFolder.exists()) {
        createFolder.mkdirs();
      }
      File file = ResourceUtils.getFile("classpath:Room_Mgmt.jrxml");
      log.info("Path of  Room_Mgmt.jrxml: {}", file);
      JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
      JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
      Map<String, Object> parameter = new HashMap<>();
      parameter.put("CreatedBy", username);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
      JasperExportManager.exportReportToPdfFile(jasperPrint, path + username + ".pdf");
      byte[] exportReportToPdf = JasperExportManager.exportReportToPdf(jasperPrint);
      HttpHeaders headers = new HttpHeaders();
      headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=invoice.pdf");
      return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(exportReportToPdf);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
