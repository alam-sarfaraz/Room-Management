package com.inn.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inn.customException.CustomException;
import com.inn.dao.UploadInvoiceDetailDao;
import com.inn.dao.UserSignUpDao;
import com.inn.model.UploadInvoiceDetail;
import com.inn.model.UserSignUp;
import com.inn.roomUtils.RoomUtils;
import com.inn.service.UploadInvoiceDetailService;
import com.inn.wrapper.UploadInvoiceDetailHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UploadInvoiceDetailServiceImpl implements UploadInvoiceDetailService {

  @Autowired
  UploadInvoiceDetailDao invoiceDetailDao;

  @Autowired
  UploadInvoiceDetailHelper detailHelper;

  @Autowired
  UserSignUpDao signUpDao;

  @Override
  public String uploadInvoiceDetail(String username, MultipartFile file) throws Exception {
    try {
      log.info("Inside the uploadInvoiceDetail : {}", username);
      log.info("File name  : {}", file.getOriginalFilename());
      if (file.isEmpty()) {
        throw new CustomException("Please upload file .......");
      }
      UserSignUp usernameDetail = signUpDao.findByUserName(username);
      if (usernameDetail == null) {
        throw new CustomException("Invalid username");
      }
      UUID uuid = UUID.randomUUID();
      String substring = uuid.toString().substring(0, 5);
      String identifier = "INV-" + substring;
      String uploadFilepathWithfileaname = detailHelper.uploadFile(file);
      UploadInvoiceDetail detail = new UploadInvoiceDetail();
      detail.setUserName(username);
      detail.setIdentifier(identifier);
      detail.setFilename(file.getOriginalFilename());
      detail.setFilePath(uploadFilepathWithfileaname);
      detail.setFileSize(file.getSize());
      detail.setFileType(file.getContentType());
      detail.setDate(RoomUtils.dateFormatter());
      detail.setTime(RoomUtils.timeFormatter());
      invoiceDetailDao.save(detail);
      return identifier;
    } catch (Exception e) {
      log.error("Error occured due to {}", e.getMessage());
      throw e;
    }
  }

  @Override
  public ResponseEntity<byte[]> downloadInvoiceByIdentifier(String identifier) throws Exception {
    try {
      log.info("Inside the downloadInvoiceByIdentifier : {}", identifier);
      UploadInvoiceDetail findByIdentifier = invoiceDetailDao.findByIdentifier(identifier);
      if (findByIdentifier == null) {
        throw new CustomException("Invalid Identifier .......");
      }

      return RoomUtils.downloadFile(findByIdentifier.getFilePath());
    } catch (Exception e) {
      log.error("Error occured due to {}", e.getMessage());
      throw e;
    }
  }

}
