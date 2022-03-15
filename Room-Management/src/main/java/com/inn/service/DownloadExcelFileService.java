package com.inn.service;

import org.springframework.http.ResponseEntity;

public interface DownloadExcelFileService {

  public ResponseEntity<byte[]> downloadAllData() throws Exception;

  public ResponseEntity<byte[]> downloadByUserName(String userName) throws Exception;
}
