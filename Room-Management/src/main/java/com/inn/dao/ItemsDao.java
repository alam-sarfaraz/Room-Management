package com.inn.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inn.model.Items;

@Repository
public interface ItemsDao extends JpaRepository<Items, Integer> {

  @Query("select items from Items items where items.userName =:un")
  List<Items> findByUserName(@Param("un") String userName);

  @Query("select it from Items it where  it.date =:dt")
  List<Items> totalItemOnDate(@Param("dt") Date date);

 @Query("select itm from Items itm where itm.date  between :st and :ed ")
 List<Items> totalItemBringBetweenTwoDate(@Param("st") Date startDate, @Param("ed") Date endDate);

}
