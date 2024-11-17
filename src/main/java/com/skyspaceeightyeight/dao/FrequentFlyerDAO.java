package com.skyspaceeightyeight.dao;

import java.util.List;

import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.domain.FrequentFlyer;





public interface FrequentFlyerDAO extends GenericDAO<FrequentFlyer, Integer> {
  
	List<FrequentFlyer> findAll();
	






}


