package com.inn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.customException.CustomException;
import com.inn.dao.UserSignUpDao;
import com.inn.model.UserSignUp;
import com.inn.roomUtils.RoomUtils;
import com.inn.service.UserSignUpService;
import com.inn.wrapper.ResetPassword;
import com.inn.wrapper.UserSignUpWrapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSignUpServiceImpl implements UserSignUpService {

  @Autowired
  UserSignUpDao signUpDao;

  @Override
  public UserSignUp create(UserSignUp userSignUp) throws Exception {
    try {
      log.info("Inside the create method :{}", userSignUp);
      // for UserName validation
      UserSignUp findByUserName = signUpDao.findByUserName(userSignUp.getUserName());
      if (findByUserName != null) {
        throw new CustomException("Username Exit Already ...........");
      }
      UserSignUp byEmailId = signUpDao.findByEmailId(userSignUp.getEmailId());
      if (byEmailId != null) {
        throw new CustomException("Email Id Present Already ...........");
      }

      userSignUp.setRole("ROLE_" + userSignUp.getRole());
      userSignUp.setDate(RoomUtils.dateFormatter());
      userSignUp.setTime(RoomUtils.timeFormatter());
      userSignUp.setModifiedTime(RoomUtils.modifiedDateTimeFormatter());
      // FOR PASSWORD VALIDATION
      RoomUtils.validatePasswordAndConfirmPassword(userSignUp.getPassword(), userSignUp.getConfirmPassword());
      // for gender validation
      RoomUtils.genderValidate(userSignUp.getGender());
      UserSignUp signUpData = signUpDao.save(userSignUp);

      return signUpData;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  // -----------------------------------------------------------------------------------------------------------

  @Override
  public UserSignUp findByEmailId(String emailId) throws Exception {
    try {
      log.info("Inside the findByEmailId method :{}", emailId);
      UserSignUp findByEmailId = signUpDao.findByEmailId(emailId);
      if (findByEmailId == null) {
        throw new CustomException("Invalid Email Id....");
      }
      return findByEmailId;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public UserSignUp findByUserName(String userName) throws Exception {
    try {
      log.info("Inside the findByUserName method :{}", userName);
      UserSignUp findByUserName = signUpDao.findByUserName(userName);
      if (findByUserName == null) {
        throw new CustomException("Invalid UserName....");
      }
      return findByUserName;
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String deleteByUserName(String userName) throws Exception {
    try {
      log.info("Inside the findByUserName method :{}", userName);
      UserSignUp findByUserName = signUpDao.findByUserName(userName);
      if (findByUserName == null) {
        throw new CustomException("Invalid UserName....");
      } else {
        Integer id = findByUserName.getId();
        signUpDao.deleteById(id);
      }
      return "User Record Deleted successFully..........";
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public UserSignUp updateByUserName(String userName, UserSignUpWrapper signUpWrapper) throws Exception {
    try {
      log.info("Inside the updateByUserName method :{}", userName, signUpWrapper);
      UserSignUp dbData = signUpDao.findByUserName(userName);
      log.info("FindByUserName :{}", dbData);
      if (dbData == null) {
        throw new CustomException("Invalid UserName ......");
      }
      UserSignUp signUp = new UserSignUp();
      signUp.setId(dbData.getId());
      signUp.setUserName(userName);
      if (signUpWrapper.getFirstName() == null) {
        signUp.setFirstName(dbData.getFirstName());
      } else {
        signUp.setFirstName(signUpWrapper.getFirstName());
      }
      if (signUpWrapper.getMiddleName() == null) {
        signUp.setMiddleName(dbData.getMiddleName());
      } else {
        signUp.setMiddleName(signUpWrapper.getMiddleName());
      }
      if (signUpWrapper.getLastName() == null) {
        signUp.setLastName(dbData.getLastName());
      } else {
        signUp.setLastName(signUpWrapper.getLastName());
      }
      if (signUpWrapper.getEmailId() == null) {
        signUp.setEmailId(dbData.getEmailId());
      } else {
        signUp.setEmailId(signUpWrapper.getEmailId());
      }
      if (signUpWrapper.getMobileNo() == null) {
        signUp.setMobileNo(dbData.getMobileNo());
      } else {
        signUp.setMobileNo(signUpWrapper.getMobileNo());
      }

      if (signUpWrapper.getGender() == null) {
        signUp.setGender(dbData.getGender());
      } else {
        signUp.setGender(signUpWrapper.getGender());
      }

      if (signUpWrapper.getRole() == null) {
        signUp.setRole(dbData.getRole());
      } else {
        signUp.setRole(signUpWrapper.getRole());
      }

      if (signUpWrapper.getPassword() == null && signUpWrapper.getConfirmPassword() == null) {
        signUp.setPassword(dbData.getPassword());
        signUp.setConfirmPassword(dbData.getConfirmPassword());
      } else {
        RoomUtils.validatePasswordAndConfirmPassword(signUpWrapper.getPassword(), signUpWrapper.getConfirmPassword());
        signUp.setPassword(signUpWrapper.getPassword());
        signUp.setConfirmPassword(signUpWrapper.getConfirmPassword());
      }
      signUp.setDate(dbData.getDate());
      signUp.setTime(dbData.getTime());
      signUp.setModifiedTime(RoomUtils.modifiedDateTimeFormatter());
      return signUpDao.save(signUp);
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

  @Override
  public String resetPassword(String userName, ResetPassword resetPassword) throws Exception {
    try {
      log.info("Inside the updateByUserName method :{}", userName, resetPassword);
      UserSignUp dbData = signUpDao.findByUserName(userName);
      if (dbData == null) {
        throw new CustomException("Invalid UserName  ....");
      }
      UserSignUp signUp = new UserSignUp();
      signUp.setId(dbData.getId());
      signUp.setUserName(userName);
      signUp.setFirstName(dbData.getFirstName());
      signUp.setMiddleName(dbData.getMiddleName());
      signUp.setLastName(dbData.getLastName());
      signUp.setEmailId(dbData.getEmailId());
      signUp.setMobileNo(dbData.getMobileNo());
      signUp.setDate(dbData.getDate());
      signUp.setTime(dbData.getTime());
      signUp.setGender(dbData.getGender());
      signUp.setRole(dbData.getRole());
      signUp.setModifiedTime(RoomUtils.modifiedDateTimeFormatter());
      RoomUtils.validatePasswordAndConfirmPassword(resetPassword.getPassword(), resetPassword.getConfirmPassword());
      signUp.setPassword(resetPassword.getPassword());
      signUp.setConfirmPassword(resetPassword.getConfirmPassword());
      signUpDao.save(signUp);
      return "PASSWORD RESET SUCCESSFULLY .......";
    } catch (Exception e) {
      log.error("Error occured due to :{}", e.getMessage());
      throw e;
    }
  }

}
