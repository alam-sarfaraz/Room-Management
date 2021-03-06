//package com.inn.securityconfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class MyConfig extends WebSecurityConfigurerAdapter {
//
//  @Bean
//  public UserDetailsService getUserDetailsService() {
//    return new UserDetailServiceImpl();
//  }
//
//  @Bean
//  public BCryptPasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public DaoAuthenticationProvider authenticationProvider() {
//    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//    authenticationProvider.setUserDetailsService(this.getUserDetailsService());
//    authenticationProvider.setPasswordEncoder(passwordEncoder());
//    return authenticationProvider;
//  }
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.authenticationProvider(authenticationProvider());
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests().antMatchers("/rest/**").hasRole("USER")
//        .antMatchers("/signup/**").permitAll().and().formLogin().and().csrf().disable();
//  }
//
//}
