package com.inn.wrapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UploadInvoiceDetailHelper {

  public String uploadFile(MultipartFile multipartFile) throws Exception {
    log.info("Inside the uploadFile :{}", multipartFile.getOriginalFilename());
    try {
      String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();
      log.info("UPLOAD_DIR :{}", UPLOAD_DIR);
      String filepathwithFilename = UPLOAD_DIR + multipartFile.getOriginalFilename();
      log.info("File path with Filename :{}", filepathwithFilename);
      Files.copy(multipartFile.getInputStream(), Paths.get(filepathwithFilename), StandardCopyOption.REPLACE_EXISTING);
      return filepathwithFilename;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }

  }
}
