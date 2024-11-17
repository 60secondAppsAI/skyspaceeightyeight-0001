package com.skyspaceeightyeight.dao;

import java.util.List;

import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.domain.FlightSchedule;





public interface FlightScheduleDAO extends GenericDAO<FlightSchedule, Integer> {
  
	List<FlightSchedule> findAll();
	






}


