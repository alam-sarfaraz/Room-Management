package com.inn.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inn.model.UserSignUp;
import com.inn.wrapper.ResetPassword;
import com.inn.wrapper.UserSignUpWrapper;

@RequestMapping("/rest/")

public interface UserDetailsRest {

  @GetMapping("/findByEmailid/{emailId}")
  UserSignUp findByEmailId(@PathVariable("emailId") String emailId) throws Exception;

  @GetMapping("/findByUserName/{userName}")
  UserSignUp findByUserName(@PathVariable("userName") String userName) throws Exception;

  @DeleteMapping("/deleteByUserName/{userName}")
  String deleteByUserName(@PathVariable("userName") String userName) throws Exception;

  @PutMapping("/updateByUserName/{userName}")
  UserSignUp updateByUserName(@PathVariable("userName") String userName, @RequestBody UserSignUpWrapper signUpWrapper)
      throws Exception;

  @PutMapping("resetPassword/{userName}")
  String resetPassword(@PathVariable("userName") String userName, @RequestBody ResetPassword resetPassword)
      throws Exception;

}
