package com.skyspaceeightyeight.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.service.GenericService;
import com.skyspaceeightyeight.service.impl.GenericServiceImpl;
import com.skyspaceeightyeight.dao.MealOptionDAO;
import com.skyspaceeightyeight.domain.MealOption;
import com.skyspaceeightyeight.dto.MealOptionDTO;
import com.skyspaceeightyeight.dto.MealOptionSearchDTO;
import com.skyspaceeightyeight.dto.MealOptionPageDTO;
import com.skyspaceeightyeight.dto.MealOptionConvertCriteriaDTO;
import com.skyspaceeightyeight.dto.common.RequestDTO;
import com.skyspaceeightyeight.dto.common.ResultDTO;
import com.skyspaceeightyeight.service.MealOptionService;
import com.skyspaceeightyeight.util.ControllerUtils;





@Service
public class MealOptionServiceImpl extends GenericServiceImpl<MealOption, Integer> implements MealOptionService {

    private final static Logger logger = LoggerFactory.getLogger(MealOptionServiceImpl.class);

	@Autowired
	MealOptionDAO mealOptionDao;

	


	@Override
	public GenericDAO<MealOption, Integer> getDAO() {
		return (GenericDAO<MealOption, Integer>) mealOptionDao;
	}
	
	public List<MealOption> findAll () {
		List<MealOption> mealOptions = mealOptionDao.findAll();
		
		return mealOptions;	
		
	}

	public ResultDTO addMealOption(MealOptionDTO mealOptionDTO, RequestDTO requestDTO) {

		MealOption mealOption = new MealOption();

		mealOption.setMealOptionId(mealOptionDTO.getMealOptionId());


		mealOption.setDescription(mealOptionDTO.getDescription());


		mealOption.setDietaryRestrictions(mealOptionDTO.getDietaryRestrictions());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		mealOption = mealOptionDao.save(mealOption);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MealOption> getAllMealOptions(Pageable pageable) {
		return mealOptionDao.findAll(pageable);
	}

	public Page<MealOption> getAllMealOptions(Specification<MealOption> spec, Pageable pageable) {
		return mealOptionDao.findAll(spec, pageable);
	}

	public ResponseEntity<MealOptionPageDTO> getMealOptions(MealOptionSearchDTO mealOptionSearchDTO) {
	
			Integer mealOptionId = mealOptionSearchDTO.getMealOptionId(); 
 			String description = mealOptionSearchDTO.getDescription(); 
 			String dietaryRestrictions = mealOptionSearchDTO.getDietaryRestrictions(); 
 			String sortBy = mealOptionSearchDTO.getSortBy();
			String sortOrder = mealOptionSearchDTO.getSortOrder();
			String searchQuery = mealOptionSearchDTO.getSearchQuery();
			Integer page = mealOptionSearchDTO.getPage();
			Integer size = mealOptionSearchDTO.getSize();

	        Specification<MealOption> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, mealOptionId, "mealOptionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			spec = ControllerUtils.andIfNecessary(spec, dietaryRestrictions, "dietaryRestrictions"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("dietaryRestrictions")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<MealOption> mealOptions = this.getAllMealOptions(spec, pageable);
		
		//System.out.println(String.valueOf(mealOptions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(mealOptions.getTotalPages()));
		
		List<MealOption> mealOptionsList = mealOptions.getContent();
		
		MealOptionConvertCriteriaDTO convertCriteria = new MealOptionConvertCriteriaDTO();
		List<MealOptionDTO> mealOptionDTOs = this.convertMealOptionsToMealOptionDTOs(mealOptionsList,convertCriteria);
		
		MealOptionPageDTO mealOptionPageDTO = new MealOptionPageDTO();
		mealOptionPageDTO.setMealOptions(mealOptionDTOs);
		mealOptionPageDTO.setTotalElements(mealOptions.getTotalElements());
		return ResponseEntity.ok(mealOptionPageDTO);
	}

	public List<MealOptionDTO> convertMealOptionsToMealOptionDTOs(List<MealOption> mealOptions, MealOptionConvertCriteriaDTO convertCriteria) {
		
		List<MealOptionDTO> mealOptionDTOs = new ArrayList<MealOptionDTO>();
		
		for (MealOption mealOption : mealOptions) {
			mealOptionDTOs.add(convertMealOptionToMealOptionDTO(mealOption,convertCriteria));
		}
		
		return mealOptionDTOs;

	}
	
	public MealOptionDTO convertMealOptionToMealOptionDTO(MealOption mealOption, MealOptionConvertCriteriaDTO convertCriteria) {
		
		MealOptionDTO mealOptionDTO = new MealOptionDTO();
		
		mealOptionDTO.setMealOptionId(mealOption.getMealOptionId());

	
		mealOptionDTO.setDescription(mealOption.getDescription());

	
		mealOptionDTO.setDietaryRestrictions(mealOption.getDietaryRestrictions());

	

		
		return mealOptionDTO;
	}

	public ResultDTO updateMealOption(MealOptionDTO mealOptionDTO, RequestDTO requestDTO) {
		
		MealOption mealOption = mealOptionDao.getById(mealOptionDTO.getMealOptionId());

		mealOption.setMealOptionId(ControllerUtils.setValue(mealOption.getMealOptionId(), mealOptionDTO.getMealOptionId()));

		mealOption.setDescription(ControllerUtils.setValue(mealOption.getDescription(), mealOptionDTO.getDescription()));

		mealOption.setDietaryRestrictions(ControllerUtils.setValue(mealOption.getDietaryRestrictions(), mealOptionDTO.getDietaryRestrictions()));



        mealOption = mealOptionDao.save(mealOption);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MealOptionDTO getMealOptionDTOById(Integer mealOptionId) {
	
		MealOption mealOption = mealOptionDao.getById(mealOptionId);
			
		
		MealOptionConvertCriteriaDTO convertCriteria = new MealOptionConvertCriteriaDTO();
		return(this.convertMealOptionToMealOptionDTO(mealOption,convertCriteria));
	}







}
