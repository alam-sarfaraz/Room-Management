package com.inn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.customException.CustomException;
import com.inn.dao.UserSignUpDao;
import com.inn.model.UserSignUp;
import com.inn.service.LogInService;
import com.inn.wrapper.UserLoginData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogInServiceImpl implements LogInService {

  @Autowired
  UserSignUpDao signUpDao;

  @Override
  public void loginUser(UserLoginData loginData) throws Exception {
    try {
      log.info("Inside the loginuserserviceImpl :{}", loginData);
      UserSignUp userData = signUpDao.findByUserName(loginData.getUsername());
      if (userData == null) {
        throw new CustomException("Invalid username .....");
      }
      if (!loginData.getPassword().equals(userData.getPassword())) {
        throw new CustomException("Incorrect passord ....");
      }

    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;

    }
  }

}
