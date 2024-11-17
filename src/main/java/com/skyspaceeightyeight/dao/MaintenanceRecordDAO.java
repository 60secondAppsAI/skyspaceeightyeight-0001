package com.skyspaceeightyeight.dao;

import java.util.List;

import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.domain.MaintenanceRecord;





public interface MaintenanceRecordDAO extends GenericDAO<MaintenanceRecord, Integer> {
  
	List<MaintenanceRecord> findAll();
	






}


