package com.skyspaceeightyeight.dao;

import java.util.List;

import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.domain.MealOption;





public interface MealOptionDAO extends GenericDAO<MealOption, Integer> {
  
	List<MealOption> findAll();
	






}


