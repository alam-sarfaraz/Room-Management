//package com.inn.securityconfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.inn.dao.UserSignUpDao;
//import com.inn.model.UserSignUp;
//
//public class UserDetailServiceImpl implements UserDetailsService {
//
//  @Autowired
//  UserSignUpDao userSignUpDao;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    UserSignUp findByUserName = userSignUpDao.findByUserName(username);
//    if (findByUserName == null) {
//      throw new UsernameNotFoundException("Invalid Username");
//    }
//    CustomUserDetails customUserDetails = new CustomUserDetails(findByUserName);
//    return customUserDetails;
//
//  }
//}
