package com.inn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_SIGNUP")
public class UserSignUp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "FIRST_NAME")
  @NotBlank(message = "First Name is mandatory")
  private String firstName;

  @Column(name = "MIDDLE_NAME")
  private String middleName;

  @Column(name = "LAST_NAME")
  @NotBlank(message = "Last Name is mandatory")
  private String lastName;

  @Column(name = "EMAIL_ID")
  private String emailId;

  @Column(name = "PASSWORD")
  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, max = 100, message = "Password must be in between 8 to 100 character")
  private String password;

  @NotBlank(message = "Confirm Password is mandatory")
  @Size(min = 8, max = 100, message = "Confirm Password must be in between 8 to 100 character")
  @Column(name = "CONFIRM_PASSWORD")
  private String confirmPassword;

  @NotBlank(message = "Mobile number is mandatory")
  @Column(name = "MOBILE_NUMBER")
  private String mobileNo;

  @Getter(onMethod = @__(@JsonIgnore))
  @Column(name = "CREATED_DATE")
  private String date;

  @Getter(onMethod = @__(@JsonIgnore))
  @Column(name = "CREATED_TIME")
  private String time;

  @Getter(onMethod = @__(@JsonIgnore))
  @Column(name = "MODIFIED_DATE_TIME")
  private String modifiedTime;

  @Column(name = "GENDER")
  private String gender;

  @Column(name = "ROLE")
  private String role;

}
