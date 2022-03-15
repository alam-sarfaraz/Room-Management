package com.inn.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.customException.CustomException;
import com.inn.dao.ItemsDao;
import com.inn.dao.JasperReportWrapperDao;
import com.inn.dao.PurchaseItemDao;
import com.inn.model.Items;
import com.inn.model.PurchaseItem;
import com.inn.model.UserSignUp;
import com.inn.roomUtils.RoomUtils;
import com.inn.service.PurchaseItemService;
import com.inn.service.UserSignUpService;
import com.inn.wrapper.JasperReportWrapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseItemServiceImpl implements PurchaseItemService {

  @Autowired
  PurchaseItemDao purchaseItemDao;

  @Autowired
  ItemsDao itemsDao;

  @Autowired
  UserSignUpService signUpService;

  @Autowired
  JasperReportWrapperDao jasperReportWrapperDao;

  @Override
  public PurchaseItem addItem(PurchaseItem purchaseItem) throws Exception {
    try {
      log.info("Inside the addItem :{}", purchaseItem);
      UserSignUp findByUserName = signUpService.findByUserName(purchaseItem.getUserName());
      if (findByUserName == null) {
        throw new CustomException("Invalid userName");
      }
      PurchaseItem purchase = purchaseItemDao.save(purchaseItem);
      List<Items> items = purchase.getItems();
      for (Items item : items) {
        item.setPurchaseItem(purchaseItem);
        item.setUserName(purchase.getUserName());
        item.setDate(RoomUtils.dateFormatForDbDate());
        item.setTime(RoomUtils.timeFormatter());
        Items itemsData = itemsDao.save(item);
        JasperReportWrapper jasperReportWrapper = new JasperReportWrapper(itemsData.getId(), itemsData.getDate(),
            itemsData.getTime(), itemsData.getUserName(), itemsData.getItemName(), itemsData.getQuantity(),
            itemsData.getPrice());
        jasperReportWrapperDao.save(jasperReportWrapper);
      }
      return purchase;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public List<PurchaseItem> findAll() throws Exception {
    try {
      log.info("Inside the findAll");
      List<PurchaseItem> findAll = purchaseItemDao.findAll();
      if (findAll.isEmpty()) {
        throw new CustomException("No Data present ......");
      }
      return findAll;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public List<PurchaseItem> findAllItemsByUserName(String userName) throws Exception {
    try {
      log.info("Inside the findAllItemsByUserName :{}", userName);
      UserSignUp findByUserName = signUpService.findByUserName(userName);
      if (findByUserName == null) {
        throw new CustomException("Invalid userName ......");
      }
      List<PurchaseItem> findAllItemsByUserName = purchaseItemDao.findAllItemsByUserName(userName);
      if (findAllItemsByUserName.isEmpty()) {
        throw new CustomException("No Data present corresponding userName ......");
      }
      return findAllItemsByUserName;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Map<String, Double> totalPriceCorrespondingUser(String userName) throws Exception {
    try {
      log.info("Inside the fintotalPriceCorrespondingUserdAll :{}", userName);
      List<PurchaseItem> findAllItemsByUserName = findAllItemsByUserName(userName);
      Double price = 0.0;
      for (PurchaseItem purchaseItem : findAllItemsByUserName) {
        for (Items item : purchaseItem.getItems()) {
          price += item.getPrice();
        }
      }
      Map<String, Double> map = new HashMap<>();
      map.put("Total Item bought by " + userName + " ", price);
      return map;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Map<String, Double> totalPrice() throws Exception {
    try {
      log.info("Inside the totalPrice ");
      List<PurchaseItem> findAll = findAll();
      Double price = 0.0;
      for (PurchaseItem purchaseItem : findAll) {
        for (Items items : purchaseItem.getItems()) {
          price += items.getPrice();
        }
      }
      Map<String, Double> map = new HashMap<>();
      map.put("Total price ", price);
      return map;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public List<Items> totalItemOnDate(Date date) {
    try {
      log.info("Inside the totalItemOnDate :{}", date);
      return itemsDao.totalItemOnDate(date);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public List<Items> totalItemBringBetweenTwoDate(Date startDate, Date endDate) {
    try {
      log.info("Inside the totalItemBringBetweenTwoDate :{},{} ", startDate, endDate);
      return itemsDao.totalItemBringBetweenTwoDate(startDate, endDate);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Map<String, Double> userWiseTotalAmount() throws Exception {
    try {
      Map<String, Double> userWiseTotalPrice = new HashMap<>();
      log.info("Inside the userWiseTotalAmount");
      List<Items> allData = itemsDao.findAll();
      if (allData.isEmpty() || allData == null) {
        throw new CustomException("No record is preset");
      }
      Map<String, List<Items>> mapData = allData.stream().collect(Collectors.groupingBy(e -> e.getUserName()));
      for (String username : mapData.keySet()) {
        Double price = 0.00;
        for (Items item : itemsDao.findByUserName(username)) {
          price += item.getPrice();
        }
        userWiseTotalPrice.put(username, price);
      }

      return userWiseTotalPrice;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public Map<String, Double> perHeadTotalPrice() throws Exception {
    try {
      log.info("Inside the perHeadTotalPrice");
      Map<String, Double> perHeadTotalPrice = new HashMap<>();
      // userWiseTotalAmount
      Map<String, Double> userWiseTotalAmount = this.userWiseTotalAmount();
      int noOfUser = userWiseTotalAmount.keySet().size();
      log.info("Value of the noOfUser :{}", noOfUser);
      // total price
      Map<String, Double> totalPrice = this.totalPrice();
      Double total = totalPrice.get("Total price ");
      log.info("Value of the total price :{}", total);
      Double perHead = total / (double) noOfUser;
      log.info("Value of the perHead price :{}", perHead);
      for (String username : userWiseTotalAmount.keySet()) {
        perHeadTotalPrice.put(username, userWiseTotalAmount.get(username) - perHead);
      }
      return perHeadTotalPrice;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
