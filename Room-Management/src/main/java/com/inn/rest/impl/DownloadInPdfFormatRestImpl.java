package com.inn.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.rest.DownloadInPdfFormatRest;
import com.inn.service.DownloadInPdfFormatService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DownloadInPdfFormatRestImpl implements DownloadInPdfFormatRest {

  @Autowired
  DownloadInPdfFormatService downloadInPdfFormatService;

  @Override
  public ResponseEntity<byte[]> downloadPdf() throws Exception {
    try {
      log.info("Inside the downloadPdf ");
      return downloadInPdfFormatService.downloadPdf();
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String downloadHtml() throws Exception {
    try {
      log.info("Inside the downloadPdf ");
      return downloadInPdfFormatService.downloadHtml();
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public ResponseEntity<byte[]> downloadPdfFileByUsername(String username) throws Exception {
    try {
      log.info("Inside the downloadPdfFileByUsername :{}", username);
      return downloadInPdfFormatService.downloadPdfFileByUsername(username);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
