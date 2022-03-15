package com.inn.service;

import com.inn.model.UserSignUp;
import com.inn.wrapper.ResetPassword;
import com.inn.wrapper.UserSignUpWrapper;

public interface UserSignUpService {

  UserSignUp create(UserSignUp userSignUp) throws Exception;

  UserSignUp findByEmailId(String emailId) throws Exception;

  UserSignUp findByUserName(String userName) throws Exception;

  String deleteByUserName(String userName) throws Exception;

  UserSignUp updateByUserName(String userName, UserSignUpWrapper signUpWrapper) throws Exception;

  String resetPassword(String userName, ResetPassword resetPassword) throws Exception;

}
