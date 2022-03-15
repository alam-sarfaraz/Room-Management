package com.inn.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadInvoiceDetailService {

  String uploadInvoiceDetail(String username, MultipartFile file) throws Exception;

  ResponseEntity<byte[]> downloadInvoiceByIdentifier(String identifier) throws Exception;

}
