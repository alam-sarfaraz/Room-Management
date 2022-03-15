package com.inn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.model.UploadInvoiceDetail;

@Repository
public interface UploadInvoiceDetailDao extends JpaRepository<UploadInvoiceDetail, Integer> {

  UploadInvoiceDetail findByIdentifier(String identifier);

}
