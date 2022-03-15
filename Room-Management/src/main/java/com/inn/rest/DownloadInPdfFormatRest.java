package com.inn.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rest/")
public interface DownloadInPdfFormatRest {

  @GetMapping("/downloadPdf")
  ResponseEntity<byte[]> downloadPdf() throws Exception;

  @GetMapping("/downloadHtml")
  String downloadHtml() throws Exception;

  @GetMapping("/downloadPdfFileByUsername/{username}")
  ResponseEntity<byte[]> downloadPdfFileByUsername(@PathVariable(name = "username") String username) throws Exception;

}
