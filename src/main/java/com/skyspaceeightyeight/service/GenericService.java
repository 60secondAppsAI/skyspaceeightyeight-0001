package com.skyspaceeightyeight.service;

import com.skyspaceeightyeight.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}