package com.inn.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inn.rest.ExportAllDataIntoExcelFileRest;
import com.inn.service.ExportAllDataIntoExcelFileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ExportAllDataIntoExcelFileRestImpl implements ExportAllDataIntoExcelFileRest {

  @Autowired
  ExportAllDataIntoExcelFileService exportAllDataIntoExcelFileService;

  @Override
  public ResponseEntity<byte[]> ExportDataToExcelFile() throws Exception {
    try {
      log.info("Inside the ExportDataToExcelFile ");
      return exportAllDataIntoExcelFileService.ExportDataToExcelFile();
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String ExportExcelFileDataToDB(MultipartFile file) throws Exception {
    try {
      log.info("Inside the ExportExcelFileDataToDB ");
      return exportAllDataIntoExcelFileService.ExportExcelFileDataToDB(file);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
