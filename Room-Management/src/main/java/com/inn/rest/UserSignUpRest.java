package com.inn.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inn.model.UserSignUp;

@RequestMapping("/signup")
@CrossOrigin("*")
public interface UserSignUpRest {

  @PostMapping("")
  UserSignUp create(@RequestBody UserSignUp userSignUp) throws Exception;

}
