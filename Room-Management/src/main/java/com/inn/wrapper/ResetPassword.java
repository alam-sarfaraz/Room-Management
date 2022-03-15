package com.inn.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPassword {

  private String password;
  private String confirmPassword;

}
