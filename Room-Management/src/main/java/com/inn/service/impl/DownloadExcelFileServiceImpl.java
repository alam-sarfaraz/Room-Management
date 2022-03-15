package com.inn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.dao.ItemsDao;
import com.inn.model.Items;
import com.inn.roomUtils.RoomUtils;
import com.inn.service.DownloadExcelFileService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DownloadExcelFileServiceImpl implements DownloadExcelFileService {

  @Autowired
  ItemsDao itemsDao;

  @Override
  public ResponseEntity<byte[]> downloadAllData() throws Exception {
    try {
      log.info("Inside the downloadAllData ");
      List<Items> findAll = itemsDao.findAll();
      String writeDataToExcelFile = RoomUtils.writeDataToExcelFile(findAll, null);
      log.info("File path :{}", writeDataToExcelFile);
      return RoomUtils.downloadFile(writeDataToExcelFile);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public ResponseEntity<byte[]> downloadByUserName(String userName) throws Exception {
    try {
      log.info("Inside the downloadByUserName :{}", userName);
      List<Items> findByUserName = itemsDao.findByUserName(userName);
      String writeDataToExcelFile = RoomUtils.writeDataToExcelFile(findByUserName, userName);
      log.info("File path :{}", writeDataToExcelFile);
      return RoomUtils.downloadFile(writeDataToExcelFile);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
