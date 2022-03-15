package com.inn.wrapper;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpWrapper {

  @NotBlank(message = "First Name is mandatory")
  @Size(min = 4, message = "First Name should be greater than 4 character")
  private String firstName;

  private String middleName;

  @NotBlank(message = "Last Name is mandatory")
  private String lastName;

  @NotBlank(message = "Email is mandatory")
  private String emailId;

  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, max = 100, message = "Password must be in between 8 to 100 character")
  private String password;

  @NotBlank(message = "Confirm password is mandatory")
  @Size(min = 8, max = 100, message = "Confirm password must be in between 8 to 100 character")
  private String confirmPassword;

  @NotBlank(message = "Mobile number is mandatory")
  private String mobileNo;

  @NotBlank(message = "Gender name is mandatory")
  private String gender;

  @NotBlank(message = "Role name is mandatory")
  private String role;

}
