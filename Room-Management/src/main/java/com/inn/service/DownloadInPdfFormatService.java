package com.inn.service;

import org.springframework.http.ResponseEntity;

public interface DownloadInPdfFormatService {

  public ResponseEntity<byte[]> downloadPdf() throws Exception;

  public String downloadHtml() throws Exception;

  public ResponseEntity<byte[]> downloadPdfFileByUsername(String username) throws Exception;

}
