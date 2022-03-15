package com.inn.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.inn.rest.LogInRest;
import com.inn.service.LogInService;
import com.inn.wrapper.UserLoginData;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LogInRestImpl implements LogInRest {

  @Autowired
  LogInService logInService;

  @Override
  public void loginUser(UserLoginData loginData) throws Exception {
    try {
      log.info("Inside the login method :{}", loginData);
       logInService.loginUser(loginData);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
