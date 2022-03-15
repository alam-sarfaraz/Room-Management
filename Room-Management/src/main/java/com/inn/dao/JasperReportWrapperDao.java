package com.inn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.wrapper.JasperReportWrapper;

@Repository
public interface JasperReportWrapperDao extends JpaRepository<JasperReportWrapper, Integer> {

}
