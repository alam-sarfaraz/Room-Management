package com.inn.wrapper;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JASPER_REPORT_WRAPPER")
public class JasperReportWrapper {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "DATE")
  private Date date;

  @Column(name = "TIME")
  private String time;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "ITEM_NAME")
  @NotBlank(message = "Item Name is mandatory")
  private String itemName;

  @Column(name = "QUANTITY")
  private String quantity;

  @Column(name = "PRICE")
  private Double price;

}
