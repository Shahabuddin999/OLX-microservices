package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olx.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Integer>{
	
	List<UserEntity> findByUsername(String username);
	
	@Query("SELECT ue from UserEntity ue where ue.username=:username ")
	public List<UserEntity> findUserByUserName(@Param("username") String username);
}
