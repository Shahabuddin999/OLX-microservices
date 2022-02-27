package com.olx.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer>{

	@Query("SELECT ae from AdvertiseEntity ae where ae.postedBy=:postedBy")
	public List<AdvertiseEntity> findByPostedBy(@Param("postedBy") String postedBy);
	
	@Query("SELECT ae from AdvertiseEntity ae where ae.description LIKE %:serchText% or ae.title LIKE %:serchText%")
	public List<AdvertiseEntity> findUserByText(@Param("serchText") String serchText);
	
	@Query("SELECT ae from AdvertiseEntity ae where ae.id=:id and ae.userName=:userName")
	public Optional<AdvertiseEntity> findByUserNameAndPostedBy(@Param("id") int id, @Param("userName") String userName);
}
