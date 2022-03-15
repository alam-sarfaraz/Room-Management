package com.inn.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.rest.DownloadExcelFileSheetRest;
import com.inn.service.DownloadExcelFileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DownloadExcelFileSheetRestImpl implements DownloadExcelFileSheetRest {

  @Autowired
  DownloadExcelFileService downloadExcelFileService;

  @Override
  public ResponseEntity<byte[]> downloadAllData() throws Exception {
    try {
      log.info("Inside the downloadAllData ");
      return downloadExcelFileService.downloadAllData();
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public ResponseEntity<byte[]> downloadByUserName(String userName) throws Exception {
    try {
      log.info("Inside the downloadByUserName :{}", userName);
      return downloadExcelFileService.downloadByUserName(userName);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
