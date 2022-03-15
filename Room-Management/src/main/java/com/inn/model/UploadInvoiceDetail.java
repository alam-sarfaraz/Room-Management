package com.inn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INVOICE_DETAIL")
public class UploadInvoiceDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "IDENTIFIER")
  private String identifier;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "FILE_NAME")
  private String filename;

  @Column(name = "FILE_TYPE")
  private String fileType;

  @Column(name = "FILE_PATH")
  private String filePath;

  @Column(name = "FILE_SIZE")
  private Long fileSize;

  @Column(name = "DATE")
  private String date;

  @Column(name = "TIME")
  private String time;

}
