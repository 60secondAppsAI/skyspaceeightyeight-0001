package com.skyspaceeightyeight.dao;

import java.util.List;

import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.domain.LoungeAccess;





public interface LoungeAccessDAO extends GenericDAO<LoungeAccess, Integer> {
  
	List<LoungeAccess> findAll();
	






}


