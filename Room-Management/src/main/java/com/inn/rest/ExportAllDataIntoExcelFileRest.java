package com.inn.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/rest/")
public interface ExportAllDataIntoExcelFileRest {

  @GetMapping("/ExportDataToExcelFile")
  ResponseEntity<byte[]> ExportDataToExcelFile() throws Exception;

  @PostMapping(path = "/ExportExcelFileDataToDB", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  String ExportExcelFileDataToDB(@RequestPart(name = "file") MultipartFile file) throws Exception;

}
