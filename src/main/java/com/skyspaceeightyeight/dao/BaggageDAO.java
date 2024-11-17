package com.skyspaceeightyeight.dao;

import java.util.List;

import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.domain.Baggage;





public interface BaggageDAO extends GenericDAO<Baggage, Integer> {
  
	List<Baggage> findAll();
	






}


