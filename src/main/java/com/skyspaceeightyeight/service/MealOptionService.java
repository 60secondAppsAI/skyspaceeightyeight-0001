package com.skyspaceeightyeight.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeightyeight.domain.MealOption;
import com.skyspaceeightyeight.dto.MealOptionDTO;
import com.skyspaceeightyeight.dto.MealOptionSearchDTO;
import com.skyspaceeightyeight.dto.MealOptionPageDTO;
import com.skyspaceeightyeight.dto.MealOptionConvertCriteriaDTO;
import com.skyspaceeightyeight.service.GenericService;
import com.skyspaceeightyeight.dto.common.RequestDTO;
import com.skyspaceeightyeight.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MealOptionService extends GenericService<MealOption, Integer> {

	List<MealOption> findAll();

	ResultDTO addMealOption(MealOptionDTO mealOptionDTO, RequestDTO requestDTO);

	ResultDTO updateMealOption(MealOptionDTO mealOptionDTO, RequestDTO requestDTO);

    Page<MealOption> getAllMealOptions(Pageable pageable);

    Page<MealOption> getAllMealOptions(Specification<MealOption> spec, Pageable pageable);

	ResponseEntity<MealOptionPageDTO> getMealOptions(MealOptionSearchDTO mealOptionSearchDTO);
	
	List<MealOptionDTO> convertMealOptionsToMealOptionDTOs(List<MealOption> mealOptions, MealOptionConvertCriteriaDTO convertCriteria);

	MealOptionDTO getMealOptionDTOById(Integer mealOptionId);







}





