package com.inn.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ExportAllDataIntoExcelFileService {

  ResponseEntity<byte[]> ExportDataToExcelFile() throws Exception;

  String ExportExcelFileDataToDB(MultipartFile file) throws Exception;

}
