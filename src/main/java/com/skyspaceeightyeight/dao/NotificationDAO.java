package com.skyspaceeightyeight.dao;

import java.util.List;

import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.domain.Notification;





public interface NotificationDAO extends GenericDAO<Notification, Integer> {
  
	List<Notification> findAll();
	






}


