package com.inn.rest.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.inn.model.Items;
import com.inn.model.PurchaseItem;
import com.inn.rest.PurchaseItemRest;
import com.inn.service.PurchaseItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PurchaseItemRestImpl implements PurchaseItemRest {

  @Autowired
  PurchaseItemService purchaseItemService;

  @Override
  public PurchaseItem addItem(PurchaseItem purchaseItem) throws Exception {
    try {
      log.info("Inside the addItem :{}", purchaseItem);
      return purchaseItemService.addItem(purchaseItem);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public List<PurchaseItem> findAll() throws Exception {
    try {
      log.info("Inside the findAll");
      return purchaseItemService.findAll();
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public List<PurchaseItem> findAllItemsByUserName(String userName) throws Exception {
    try {
      log.info("Inside the findAllItemsByUserName :{}", userName);
      return purchaseItemService.findAllItemsByUserName(userName);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Map<String, Double> totalPriceCorrespondingUser(String userName) throws Exception {
    try {
      log.info("Inside the totalPriceCorrespondingUser :{}", userName);
      return purchaseItemService.totalPriceCorrespondingUser(userName);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Map<String, Double> totalPrice() throws Exception {
    try {
      log.info("Inside the totalPrice ");
      return purchaseItemService.totalPrice();
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public List<Items> totalItemOnDate(Date date) {
    try {
      log.info("Inside the totalItemOnDate :{}", date);
      return purchaseItemService.totalItemOnDate(date);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public List<Items> totalItemBringBetweenTwoDate(Date startDate, Date endDate) {
    try {
      log.info("Inside the totalItemBringBetweenTwoDate :{},{} ", startDate, endDate);
      return purchaseItemService.totalItemBringBetweenTwoDate(startDate, endDate);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Map<String, Double> userWiseTotalAmount() throws Exception {
    try {
      log.info("Inside the userWiseTotalAmount");
      return purchaseItemService.userWiseTotalAmount();
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Map<String, Double> perHeadTotalPrice() throws Exception {
    try {
      log.info("Inside the perHeadTotalPrice");
      return purchaseItemService.perHeadTotalPrice();
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
