package com.skyspaceeightyeight.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeightyeight.domain.Airline;
import com.skyspaceeightyeight.dto.AirlineDTO;
import com.skyspaceeightyeight.dto.AirlineSearchDTO;
import com.skyspaceeightyeight.dto.AirlinePageDTO;
import com.skyspaceeightyeight.dto.AirlineConvertCriteriaDTO;
import com.skyspaceeightyeight.service.GenericService;
import com.skyspaceeightyeight.dto.common.RequestDTO;
import com.skyspaceeightyeight.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AirlineService extends GenericService<Airline, Integer> {

	List<Airline> findAll();

	ResultDTO addAirline(AirlineDTO airlineDTO, RequestDTO requestDTO);

	ResultDTO updateAirline(AirlineDTO airlineDTO, RequestDTO requestDTO);

    Page<Airline> getAllAirlines(Pageable pageable);

    Page<Airline> getAllAirlines(Specification<Airline> spec, Pageable pageable);

	ResponseEntity<AirlinePageDTO> getAirlines(AirlineSearchDTO airlineSearchDTO);
	
	List<AirlineDTO> convertAirlinesToAirlineDTOs(List<Airline> airlines, AirlineConvertCriteriaDTO convertCriteria);

	AirlineDTO getAirlineDTOById(Integer airlineId);







}





