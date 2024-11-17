package com.skyspaceeightyeight.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeightyeight.domain.FlightSchedule;
import com.skyspaceeightyeight.dto.FlightScheduleDTO;
import com.skyspaceeightyeight.dto.FlightScheduleSearchDTO;
import com.skyspaceeightyeight.dto.FlightSchedulePageDTO;
import com.skyspaceeightyeight.dto.FlightScheduleConvertCriteriaDTO;
import com.skyspaceeightyeight.service.GenericService;
import com.skyspaceeightyeight.dto.common.RequestDTO;
import com.skyspaceeightyeight.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightScheduleService extends GenericService<FlightSchedule, Integer> {

	List<FlightSchedule> findAll();

	ResultDTO addFlightSchedule(FlightScheduleDTO flightScheduleDTO, RequestDTO requestDTO);

	ResultDTO updateFlightSchedule(FlightScheduleDTO flightScheduleDTO, RequestDTO requestDTO);

    Page<FlightSchedule> getAllFlightSchedules(Pageable pageable);

    Page<FlightSchedule> getAllFlightSchedules(Specification<FlightSchedule> spec, Pageable pageable);

	ResponseEntity<FlightSchedulePageDTO> getFlightSchedules(FlightScheduleSearchDTO flightScheduleSearchDTO);
	
	List<FlightScheduleDTO> convertFlightSchedulesToFlightScheduleDTOs(List<FlightSchedule> flightSchedules, FlightScheduleConvertCriteriaDTO convertCriteria);

	FlightScheduleDTO getFlightScheduleDTOById(Integer flightScheduleId);







}





