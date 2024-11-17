package com.skyspaceeightyeight.dao;

import java.util.List;

import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


