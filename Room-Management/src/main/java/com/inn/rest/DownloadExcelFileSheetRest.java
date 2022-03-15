package com.inn.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rest/")
public interface DownloadExcelFileSheetRest {

  @GetMapping("/downloadExcelFile")
  ResponseEntity<byte[]> downloadAllData() throws Exception;

  @GetMapping("/downloadExcelFileByUserName/{userName}")
  ResponseEntity<byte[]> downloadByUserName(@PathVariable("userName") String userName) throws Exception;


}
