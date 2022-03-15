package com.inn.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.inn.model.Items;
import com.inn.model.PurchaseItem;

public interface PurchaseItemService {

  PurchaseItem addItem(PurchaseItem purchaseItem) throws Exception;

  List<PurchaseItem> findAll() throws Exception;

  List<PurchaseItem> findAllItemsByUserName(String userName) throws Exception;

  Map<String, Double> totalPriceCorrespondingUser(String userName) throws Exception;

  Map<String, Double> totalPrice() throws Exception;

  List<Items> totalItemOnDate(Date date);

  List<Items> totalItemBringBetweenTwoDate(Date startDate, Date endDate);

  Map<String, Double> userWiseTotalAmount() throws Exception;

  Map<String, Double> perHeadTotalPrice() throws Exception;

}
