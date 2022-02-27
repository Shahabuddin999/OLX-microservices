package com.olx.service;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.olx.dto.AdvertiseDto;
import com.olx.dto.AdvertiseStatusDto;
import com.olx.dto.CategoryDto;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.AdvertiseNotFoundException;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.repository.AdvertiseRepository;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class AdvertiseServiceImpl implements AdvertiseService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AdvertiseRepository advertiseRepository;
	
	@Autowired
	MasterDelegateService masterDelegateService;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<AdvertiseDto> createAdvertise(AdvertiseDto advertiseDto , String authorization) {
		
		ResponseEntity<Boolean> result = masterDelegateService.isValivUser(authorization);
		if(Boolean.TRUE.equals(result.getBody())) {
			AdvertiseEntity advertiseEntity = new AdvertiseEntity();
			ResponseEntity<String> userName = masterDelegateService.getUserName(authorization);
			advertiseEntity = this.modelMapper.map(advertiseDto, AdvertiseEntity.class);
			advertiseEntity.setCreatedDate(LocalDate.now());
			advertiseEntity.setModifiedDate(LocalDate.now());
			advertiseEntity.setUserName(userName.getBody());
			advertiseEntity.setPostedBy(userName.getBody());
			advertiseEntity = advertiseRepository.save(advertiseEntity);
			advertiseDto = this.modelMapper.map(advertiseEntity, AdvertiseDto.class);
			return new ResponseEntity(advertiseEntity,HttpStatus.CREATED);
		} else {
			throw new InvalidAuthTokenException("You have intered invalid Auth Tocken");
		}
		
	}

	@Override
	public ResponseEntity<AdvertiseDto> updateAdvertise(AdvertiseDto advertiseDto , String authorization,
			int advertiseId) {
		ResponseEntity<Boolean> result = masterDelegateService.isValivUser(authorization);
		Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepository.findById(advertiseId);
		if(Boolean.TRUE.equals(result.getBody())) {
			ResponseEntity<String> userName = masterDelegateService.getUserName(authorization);
			if(opAdvertiseEntity.isPresent()) {
				AdvertiseEntity advertiseEntity = this.modelMapper.map(advertiseDto, AdvertiseEntity.class);
				advertiseEntity.setUserName(userName.getBody());
				advertiseEntity.setPostedBy(userName.getBody());
				advertiseEntity.setId(advertiseId);
				advertiseEntity.setModifiedDate(LocalDate.now());
				advertiseEntity.setCreatedDate(opAdvertiseEntity.get().getCreatedDate());
				advertiseEntity = advertiseRepository.save(advertiseEntity);
				advertiseDto = this.modelMapper.map(advertiseEntity, AdvertiseDto.class);
				return new ResponseEntity(advertiseDto,HttpStatus.ACCEPTED);
			} else {
				throw new AdvertiseNotFoundException("Advertise not found with this advertise Id : "+ advertiseId); 
			}
		}else
			throw new InvalidAuthTokenException("You have intered invalid Auth Tocken");
	}

	@Override
	public ResponseEntity<List<AdvertiseDto>> getAdvertiseByLoggedInUser(String authorization) {
		ResponseEntity<Boolean> result = masterDelegateService.isValivUser(authorization);
		List<AdvertiseDto> list = new ArrayList<>();
		if(Boolean.TRUE.equals(result.getBody())) {
			ResponseEntity<String> userName = masterDelegateService.getUserName(authorization);
			List<AdvertiseEntity> advertiseEntities = advertiseRepository.findByPostedBy(userName.getBody());
			if(!advertiseEntities.isEmpty()) {
				for(AdvertiseEntity advertiseEntity : advertiseEntities) {
					AdvertiseDto advertiseDto = this.modelMapper.map(advertiseEntity, AdvertiseDto.class);
					advertiseDto.setStatusName(getAdvertiseStatusDto(advertiseEntity.getStatus()).getBody().getStatus());
					advertiseDto.setCategoryName(getCategoryById(advertiseEntity.getCategory()).getBody().getName());
					list.add(advertiseDto);
				}
				return new ResponseEntity(list,HttpStatus.FOUND);
			} else {
				throw new AdvertiseNotFoundException("There is no advertise available"); 
			}
		} else
			throw new InvalidAuthTokenException("You have intered invalid Auth Tocken");
	}

	@Override
	public ResponseEntity<AdvertiseDto> getAdvertiseByLoggedInUserAndId(String authorization, int advertiseId) {
		ResponseEntity<Boolean> result = masterDelegateService.isValivUser(authorization);
		ResponseEntity responce = null;
		if(Boolean.TRUE.equals(result.getBody())) {
			ResponseEntity<String> userName = masterDelegateService.getUserName(authorization);
			Optional<AdvertiseEntity> advertiseEntity = advertiseRepository.findByUserNameAndPostedBy(advertiseId, userName.getBody());
			if(advertiseEntity.isPresent()) {
				responce = new ResponseEntity(advertiseEntity.get(),HttpStatus.FOUND);
			} else {
				throw new AdvertiseNotFoundException("Advertise not found with this advertise Id : "+ advertiseId);
			}
		} else {
			throw new InvalidAuthTokenException("You have intered invalid Auth Tocken");
		}
		return responce;
	}

	@Override
	public ResponseEntity<Boolean> deleteAdvertiseByLoggedInUserAndId(String authorization, int advertiseId) {
		
		ResponseEntity<Boolean> result = masterDelegateService.isValivUser(authorization);
		if(Boolean.TRUE.equals(result.getBody())) {
			ResponseEntity<String> userName = masterDelegateService.getUserName(authorization);
			Optional<AdvertiseEntity> advertiseEntity = advertiseRepository.findByUserNameAndPostedBy(advertiseId, userName.getBody());
			if(advertiseEntity.isPresent()) {
				advertiseRepository.delete(advertiseEntity.get());
				return new ResponseEntity(advertiseEntity.get(),HttpStatus.ACCEPTED);
			} else {
				throw new AdvertiseNotFoundException("Advertise not found with this advertise Id : "+ advertiseId);
			}
		}else {
			throw new InvalidAuthTokenException("You have intered invalid Auth Tocken");
		}
		//return result;
		
	}

	@Override
	public ResponseEntity<List<AdvertiseDto>> getAdvertiseByText(String searchText) {
		List<AdvertiseEntity> advertiseEntities = advertiseRepository.findUserByText(searchText);
		List<AdvertiseDto> list = new ArrayList<>();
		if(!advertiseEntities.isEmpty()) {
			for(AdvertiseEntity AdvertiseEntity : advertiseEntities)
				list.add(this.modelMapper.map(AdvertiseEntity, AdvertiseDto.class));
			return new ResponseEntity(list,HttpStatus.FOUND);
		} else {
			throw new AdvertiseNotFoundException("Advertise not found with this this text : "+ searchText);
		}
	}

	@Override
	public ResponseEntity<AdvertiseDto> getAdvertiseById(String authorization, int advertiseId) {
		ResponseEntity<Boolean> result = masterDelegateService.isValivUser(authorization);
		
		if(Boolean.TRUE.equals(result.getBody())) {
			AdvertiseDto advertiseDto = new AdvertiseDto();
			ResponseEntity<String> userName = masterDelegateService.getUserName(authorization);
			Optional<AdvertiseEntity> advertiseEntity = advertiseRepository.findByUserNameAndPostedBy(advertiseId, userName.getBody());
			if(advertiseEntity.isPresent()) {
				advertiseDto = this.modelMapper.map(advertiseEntity.get(), AdvertiseDto.class);
				advertiseDto.setStatusName(masterDelegateService.getAdvertiseStatusDto(advertiseId).getBody().getStatus());
				advertiseDto.setCategoryName(masterDelegateService.getCategoryById(advertiseEntity.get().getCategory()).getBody().getName());
				return new ResponseEntity(advertiseDto,HttpStatus.FOUND);
			} else {
				throw new AdvertiseNotFoundException("Advertise not found with this this Id : "+ advertiseId);
			}
		} else {
			throw new InvalidAuthTokenException("You have intered invalid Auth Tocken");
		}
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		return masterDelegateService.getAllCategory();
	}

	@Override
	public ResponseEntity<CategoryDto> getCategoryById(Integer categoryId) {
		return masterDelegateService.getCategoryById(categoryId);
	}

	@Override
	public ResponseEntity<AdvertiseStatusDto> getAdvertiseStatusDto(Integer categoryId) {
		return masterDelegateService.getAdvertiseStatusDto(categoryId);
	}

	@Override
	public List<AdvertiseDto> searchAdvertisesByFilterCriteria(String searchText, int categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> rootEntity = criteriaQuery.from(AdvertiseEntity.class);
		Predicate predicateTitle = null;
		Predicate predicateDescription = null;
		Predicate predicatePostedBy = null;
		Predicate predicateOnDate = null;
		if(searchText != null && !searchText.equals("")) {
			//predicateTitle = criteriaBuilder.like(rootEntity.get("title"), searchText);
			predicateDescription = criteriaBuilder.like(rootEntity.get("description"), searchText);
			criteriaQuery = criteriaQuery.where(predicateDescription);
			//criteriaQuery = criteriaQuery.where(predicateTitle,predicateDescription);
		}
		//criteriaQuery.where(predicateTitle,predicateDescription);
		
		if(postedBy != null && !postedBy.equals("")) {
			predicatePostedBy = criteriaBuilder.equal(rootEntity.get("postedBy"), postedBy);
			criteriaQuery = criteriaQuery.where(predicatePostedBy);
		}
//		
//		if(dateCondition != null && !dateCondition.equals("")) {
//			if(dateCondition.equals("equals")) {
//				predicateOnDate = criteriaBuilder.equal(rootEntity.get("postedBy"), postedBy);
//				criteriaQuery.where(predicateOnDate);
//
//			}
//		}
		
		TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		//typedQuery.setFirstResult(startIndex);
		//typedQuery.setMaxResults(records);
		List<AdvertiseEntity> list = typedQuery.getResultList();
		List<AdvertiseDto> listDto = new ArrayList<>();
		for(AdvertiseEntity advertiseEntity : list) {
			AdvertiseDto dto = this.modelMapper.map(advertiseEntity, AdvertiseDto.class);
			listDto.add(dto);
		}
		return listDto;
	}
	@Override
	public AdvertiseDto createAdvertiseDummy(AdvertiseDto advertiseDto , String authorization) {
		
		ResponseEntity<Boolean> result = masterDelegateService.isValivUser(authorization);
		AdvertiseEntity advertiseEntity = new AdvertiseEntity();
		if(Boolean.TRUE.equals(result.getBody())) {
			ResponseEntity<String> userName = masterDelegateService.getUserName(authorization);
			advertiseEntity = this.modelMapper.map(advertiseDto, AdvertiseEntity.class);
			advertiseEntity.setUserName(userName.getBody());
			advertiseEntity.setPostedBy(userName.getBody());
			advertiseEntity = advertiseRepository.save(advertiseEntity);
			advertiseDto = this.modelMapper.map(advertiseEntity, AdvertiseDto.class);
		}
		return advertiseDto;
	}

	@Override
	public ResponseEntity<List<AdvertiseDto>> getAdvertiseByCriteria(String searchText, int categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> rootEntity = criteriaQuery.from(AdvertiseEntity.class);

		Predicate predicateSearchText = criteriaBuilder.and();
		Predicate onDateCondition = criteriaBuilder.and();
		Predicate fromDateCondition = criteriaBuilder.and();
		Predicate fromLessDateCondition = criteriaBuilder.and();
		Predicate fromToDateCondition = criteriaBuilder.and();
		Predicate predicatePostedBy = criteriaBuilder.and();
		Predicate predicateCategoryId = criteriaBuilder.and();
		Predicate preDateCondition = criteriaBuilder.and();

		Predicate predicateFinal = criteriaBuilder.and();

		if(searchText!=null && !"".equals(searchText)) {
		Predicate predicateTitle = criteriaBuilder.like(rootEntity.get("title"), "%" + searchText + "%");
		Predicate predicateDescription = criteriaBuilder.like(rootEntity.get("description"), "%" + searchText + "%");
		predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription);
		}

		if (postedBy != null && !"".equalsIgnoreCase(postedBy)) {
			predicatePostedBy = criteriaBuilder.equal(rootEntity.get("postedBy"), postedBy);
		}
		if (categoryId != 0) {
			predicateCategoryId = criteriaBuilder.equal(rootEntity.get("category"), categoryId);
		}

		if (dateCondition != null && !"".equals(dateCondition)) {

			if (dateCondition.equalsIgnoreCase("equals")) {
				onDateCondition = criteriaBuilder.equal(rootEntity.get("createdDate"), onDate);
			}
			if (dateCondition.equals("greaterthan")) {
				fromDateCondition = criteriaBuilder.greaterThan(rootEntity.get("createdDate"), fromDate);
			}

			if (dateCondition.equalsIgnoreCase("lessthan")) {
				 fromLessDateCondition =  criteriaBuilder.lessThan(rootEntity.get("createdDate"), fromDate);
			}

			if (dateCondition.equalsIgnoreCase("between")) {
				 fromToDateCondition = criteriaBuilder.between(rootEntity.get("createdDate"), fromDate, toDate);
			}
			
			preDateCondition =criteriaBuilder.or(onDateCondition,fromDateCondition,fromLessDateCondition,fromToDateCondition);
		}
		

		predicateFinal = criteriaBuilder.and(predicateSearchText,predicatePostedBy, preDateCondition, predicateCategoryId);
		criteriaQuery.where(predicateFinal);
		criteriaQuery.orderBy(criteriaBuilder.asc(rootEntity.get("title")));
		
		TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(startIndex);
		typedQuery.setMaxResults(records);
		List<AdvertiseEntity> resultEntityList = typedQuery.getResultList();

		List<AdvertiseDto> advertises = new ArrayList<>();
		for (AdvertiseEntity advertiseEntity : resultEntityList) {
			AdvertiseDto advertiseDto = this.modelMapper.map(advertiseEntity, AdvertiseDto.class);
			advertises.add(advertiseDto);
		}
		ResponseEntity<List<AdvertiseDto>> response = new ResponseEntity(advertises,HttpStatus.OK);
		return response;
	}
}
