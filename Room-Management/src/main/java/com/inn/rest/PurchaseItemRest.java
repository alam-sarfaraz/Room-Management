package com.inn.rest;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inn.model.Items;
import com.inn.model.PurchaseItem;

@RequestMapping("/rest/")
public interface PurchaseItemRest {

  @PostMapping("/additems")
  PurchaseItem addItem(@RequestBody PurchaseItem purchaseItem) throws Exception;

  @GetMapping("/findAll")
  List<PurchaseItem> findAll() throws Exception;

  @GetMapping("/findAllItemsByUserName/{userName}")
  List<PurchaseItem> findAllItemsByUserName(@PathVariable("userName") String userName) throws Exception;

  @GetMapping("/totalPriceCorrespondingUser/{userName}")
  Map<String, Double> totalPriceCorrespondingUser(@PathVariable("userName") String userName) throws Exception;

  @GetMapping("/totalPrice")
  Map<String, Double> totalPrice() throws Exception;

  @GetMapping("/totalItemOnDate")
  List<Items> totalItemOnDate(@RequestParam(required = true, name = "date") Date date);

  @GetMapping("/totalItemBringBetweenTwoDate")
  List<Items> totalItemBringBetweenTwoDate(@RequestParam(required = true, name = "startDate") Date startDate,
      @RequestParam(required = true, name = "endDate") Date endDate);

  @GetMapping("/userWiseTotalAmount")
  Map<String, Double> userWiseTotalAmount() throws Exception;

  @GetMapping("/perHeadTotalPrice")
  Map<String, Double> perHeadTotalPrice() throws Exception;
}
