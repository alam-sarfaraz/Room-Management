package com.inn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inn.model.UserSignUp;

@Repository
public interface UserSignUpDao extends JpaRepository<UserSignUp, Integer> {

	@Query("select us from UserSignUp us where us.emailId =:em")
	UserSignUp findByEmailId(@Param("em") String emailId);

	@Query("select us from UserSignUp us where us.userName =:un")
	UserSignUp findByUserName(@Param("un") String userName);

}
