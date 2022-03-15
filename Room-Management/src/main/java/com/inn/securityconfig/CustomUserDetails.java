//package com.inn.securityconfig;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.inn.model.UserSignUp;
//
//public class CustomUserDetails implements UserDetails {
//
//  private UserSignUp userSignUp;
//
//  public CustomUserDetails(UserSignUp userSignUp) {
//    super();
//    this.userSignUp = userSignUp;
//  }
//
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userSignUp.getRole());
//    return List.of(simpleGrantedAuthority);
//  }
//
//  @Override
//  public String getPassword() {
//    return userSignUp.getPassword();
//  }
//
//  @Override
//  public String getUsername() {
//    return userSignUp.getUserName();
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return true;
//  }
//
//}
