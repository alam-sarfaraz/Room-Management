package com.inn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inn.model.PurchaseItem;

@Repository
public interface PurchaseItemDao extends JpaRepository<PurchaseItem, Integer> {

  @Query("select pi from PurchaseItem pi where pi.userName =:un")
  List<PurchaseItem> findAllItemsByUserName(@Param("un") String userName);

}
