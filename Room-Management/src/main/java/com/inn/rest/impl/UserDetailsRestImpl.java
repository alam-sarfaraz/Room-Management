package com.inn.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.inn.model.UserSignUp;
import com.inn.rest.UserDetailsRest;
import com.inn.service.UserSignUpService;
import com.inn.wrapper.ResetPassword;
import com.inn.wrapper.UserSignUpWrapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserDetailsRestImpl implements UserDetailsRest {

  @Autowired
  UserSignUpService signUpService;

  @Override
  public UserSignUp findByEmailId(String emailId) throws Exception {
    try {
      log.info("Inside the findByEmailId method :{}", emailId);
      return signUpService.findByEmailId(emailId);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public UserSignUp findByUserName(String userName) throws Exception {
    try {
      log.info("Inside the findByUserName method :{}", userName);
      return signUpService.findByUserName(userName);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String deleteByUserName(String userName) throws Exception {
    try {
      log.info("Inside the deleteByUserName method :{}", userName);
      return signUpService.deleteByUserName(userName);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public UserSignUp updateByUserName(String userName, UserSignUpWrapper signUpWrapper) throws Exception {
    try {
      log.info("Inside the updateByUserName method :{},{}", userName, signUpWrapper);
      return signUpService.updateByUserName(userName, signUpWrapper);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String resetPassword(String userName, ResetPassword resetPassword) throws Exception {
    try {
      log.info("Inside the resetPassword method :{},{}", userName, resetPassword);
      return signUpService.resetPassword(userName, resetPassword);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
