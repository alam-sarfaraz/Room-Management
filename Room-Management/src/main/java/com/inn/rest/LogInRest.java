package com.inn.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inn.wrapper.UserLoginData;

@RequestMapping("/login/")
@CrossOrigin("*")
public interface LogInRest {

  @PostMapping("/")
  public void loginUser(@RequestBody UserLoginData loginData) throws Exception;

}
