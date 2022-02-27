package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.document.BlackListedToken;

public interface UserLogoutMongoRepository extends MongoRepository<BlackListedToken, Integer>{
	BlackListedToken findByToken(String token);
}
