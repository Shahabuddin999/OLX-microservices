package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.AdvertiseStatusDto;
import com.olx.dto.CategoryDto;
import com.olx.entity.AdvertiseStatusEntity;
import com.olx.entity.CategoryEntity;
import com.olx.repository.AdvertiseStatusRepository;
import com.olx.repository.CategoryRepository;
@Service
public class MasterServiceImpl implements MasterService{
	
	@Autowired
	AdvertiseStatusRepository advertiseStatusRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<List<CategoryDto>> getAdvertise() {
		List<CategoryEntity> categoryEntity = categoryRepository.findAll();
		if(!categoryEntity.isEmpty()) {
			List<CategoryDto> categoryDto = new ArrayList<>();
			for(CategoryEntity entity : categoryEntity) {
				categoryDto.add(this.modelMapper.map(entity, CategoryDto.class));
			}
			return new ResponseEntity(categoryDto,HttpStatus.FOUND);
		} else {
			return new ResponseEntity(new ArrayList<CategoryDto>(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<AdvertiseStatusDto>> getStatus() {
		List<AdvertiseStatusEntity> advertiseStatusEntities = advertiseStatusRepository.findAll();
		if(!advertiseStatusEntities.isEmpty()) {
			List<AdvertiseStatusDto> advertiseStatusDto = new ArrayList<AdvertiseStatusDto>();
			for(AdvertiseStatusEntity entity : advertiseStatusEntities) {
				advertiseStatusDto.add(this.modelMapper.map(entity, AdvertiseStatusDto.class));
			}
			return new ResponseEntity(advertiseStatusDto,HttpStatus.FOUND);
		} else {
			return new ResponseEntity(new ArrayList<CategoryDto>(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<CategoryDto> getCategoryById(Integer categoryId) {
		Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
		if(categoryEntity.isPresent()) {
			CategoryDto categoryDto = this.modelMapper.map(categoryEntity.get(), CategoryDto.class);
			return new ResponseEntity(categoryDto,HttpStatus.FOUND);
		}
		return new ResponseEntity(new CategoryDto(),HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<AdvertiseStatusDto> getAdvertiseStatusDto(Integer advertiseStatusDtoStatusId) {
		Optional<AdvertiseStatusEntity> advertiseStatusEntity = advertiseStatusRepository.findById(advertiseStatusDtoStatusId);
		AdvertiseStatusDto advertiseStatusDto = new AdvertiseStatusDto();
		if(advertiseStatusEntity.isPresent()) {
			advertiseStatusDto = this.modelMapper.map(advertiseStatusEntity.get(), AdvertiseStatusDto.class);
			return new ResponseEntity(advertiseStatusDto,HttpStatus.FOUND);
		}
		return new ResponseEntity(new AdvertiseStatusDto(),HttpStatus.NOT_FOUND);
	}

}
