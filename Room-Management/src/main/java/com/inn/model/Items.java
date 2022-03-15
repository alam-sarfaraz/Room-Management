package com.inn.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEMS")
public class Items {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "ITEM_NAME")
  @NotBlank(message = "Item Name is mandatory")
  private String itemName;

  @Column(name = "QUANTITY")
  private String quantity;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "PRICE")
  private Double price;

  @Column(name = "DATE")
  private Date date;

  @Column(name = "TIME")
  private String time;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_FK", referencedColumnName = "ID")
  @Getter(onMethod_ = @JsonIgnore)
  private PurchaseItem purchaseItem;
}
