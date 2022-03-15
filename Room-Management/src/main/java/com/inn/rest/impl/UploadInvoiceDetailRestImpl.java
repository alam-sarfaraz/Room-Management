package com.inn.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inn.rest.UploadInvoiceDetailRest;
import com.inn.service.UploadInvoiceDetailService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UploadInvoiceDetailRestImpl implements UploadInvoiceDetailRest {

  @Autowired
  UploadInvoiceDetailService invoiceDetailService;

  @Override
  public String uploadInvoiceDetail(String username, MultipartFile file) throws Exception {
    try {
      log.info("Inside the uploadInvoiceDetail : {} {}", username, file.getOriginalFilename());
      return invoiceDetailService.uploadInvoiceDetail(username, file);
    } catch (Exception e) {
      log.error("Error occured due to {}", e.getMessage());
      throw e;
    }
  }

  @Override
  public ResponseEntity<byte[]> downloadInvoiceByIdentifier(String identifier) throws Exception {
    try {
      log.info("Inside the downloadInvoiceByIdentifier : {}" ,identifier);
      return invoiceDetailService.downloadInvoiceByIdentifier(identifier);
    } catch (Exception e) {
      log.error("Error occured due to {}", e.getMessage());
      throw e;
    }
  }

}
