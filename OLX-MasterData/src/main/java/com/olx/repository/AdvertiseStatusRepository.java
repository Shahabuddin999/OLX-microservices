package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.AdvertiseStatusEntity;

public interface AdvertiseStatusRepository extends JpaRepository<AdvertiseStatusEntity, Integer>{
	
}
