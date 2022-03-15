package com.inn.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.inn.model.UserSignUp;
import com.inn.rest.UserSignUpRest;
import com.inn.service.UserSignUpService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserSignUpRestImpl implements UserSignUpRest {

  @Autowired
  UserSignUpService signUpService;

  @Override
  public UserSignUp create(UserSignUp userSignUp) throws Exception {
    try {
      log.info("Inside the create method :{}", userSignUp);
      return signUpService.create(userSignUp);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }

  }

}
