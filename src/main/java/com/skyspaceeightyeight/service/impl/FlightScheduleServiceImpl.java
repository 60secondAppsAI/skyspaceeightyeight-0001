package com.skyspaceeightyeight.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspaceeightyeight.dao.GenericDAO;
import com.skyspaceeightyeight.service.GenericService;
import com.skyspaceeightyeight.service.impl.GenericServiceImpl;
import com.skyspaceeightyeight.dao.FlightScheduleDAO;
import com.skyspaceeightyeight.domain.FlightSchedule;
import com.skyspaceeightyeight.dto.FlightScheduleDTO;
import com.skyspaceeightyeight.dto.FlightScheduleSearchDTO;
import com.skyspaceeightyeight.dto.FlightSchedulePageDTO;
import com.skyspaceeightyeight.dto.FlightScheduleConvertCriteriaDTO;
import com.skyspaceeightyeight.dto.common.RequestDTO;
import com.skyspaceeightyeight.dto.common.ResultDTO;
import com.skyspaceeightyeight.service.FlightScheduleService;
import com.skyspaceeightyeight.util.ControllerUtils;





@Service
public class FlightScheduleServiceImpl extends GenericServiceImpl<FlightSchedule, Integer> implements FlightScheduleService {

    private final static Logger logger = LoggerFactory.getLogger(FlightScheduleServiceImpl.class);

	@Autowired
	FlightScheduleDAO flightScheduleDao;

	


	@Override
	public GenericDAO<FlightSchedule, Integer> getDAO() {
		return (GenericDAO<FlightSchedule, Integer>) flightScheduleDao;
	}
	
	public List<FlightSchedule> findAll () {
		List<FlightSchedule> flightSchedules = flightScheduleDao.findAll();
		
		return flightSchedules;	
		
	}

	public ResultDTO addFlightSchedule(FlightScheduleDTO flightScheduleDTO, RequestDTO requestDTO) {

		FlightSchedule flightSchedule = new FlightSchedule();

		flightSchedule.setFlightScheduleId(flightScheduleDTO.getFlightScheduleId());


		flightSchedule.setDepartureDate(flightScheduleDTO.getDepartureDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		flightSchedule = flightScheduleDao.save(flightSchedule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<FlightSchedule> getAllFlightSchedules(Pageable pageable) {
		return flightScheduleDao.findAll(pageable);
	}

	public Page<FlightSchedule> getAllFlightSchedules(Specification<FlightSchedule> spec, Pageable pageable) {
		return flightScheduleDao.findAll(spec, pageable);
	}

	public ResponseEntity<FlightSchedulePageDTO> getFlightSchedules(FlightScheduleSearchDTO flightScheduleSearchDTO) {
	
			Integer flightScheduleId = flightScheduleSearchDTO.getFlightScheduleId(); 
   			String sortBy = flightScheduleSearchDTO.getSortBy();
			String sortOrder = flightScheduleSearchDTO.getSortOrder();
			String searchQuery = flightScheduleSearchDTO.getSearchQuery();
			Integer page = flightScheduleSearchDTO.getPage();
			Integer size = flightScheduleSearchDTO.getSize();

	        Specification<FlightSchedule> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, flightScheduleId, "flightScheduleId"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<FlightSchedule> flightSchedules = this.getAllFlightSchedules(spec, pageable);
		
		//System.out.println(String.valueOf(flightSchedules.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(flightSchedules.getTotalPages()));
		
		List<FlightSchedule> flightSchedulesList = flightSchedules.getContent();
		
		FlightScheduleConvertCriteriaDTO convertCriteria = new FlightScheduleConvertCriteriaDTO();
		List<FlightScheduleDTO> flightScheduleDTOs = this.convertFlightSchedulesToFlightScheduleDTOs(flightSchedulesList,convertCriteria);
		
		FlightSchedulePageDTO flightSchedulePageDTO = new FlightSchedulePageDTO();
		flightSchedulePageDTO.setFlightSchedules(flightScheduleDTOs);
		flightSchedulePageDTO.setTotalElements(flightSchedules.getTotalElements());
		return ResponseEntity.ok(flightSchedulePageDTO);
	}

	public List<FlightScheduleDTO> convertFlightSchedulesToFlightScheduleDTOs(List<FlightSchedule> flightSchedules, FlightScheduleConvertCriteriaDTO convertCriteria) {
		
		List<FlightScheduleDTO> flightScheduleDTOs = new ArrayList<FlightScheduleDTO>();
		
		for (FlightSchedule flightSchedule : flightSchedules) {
			flightScheduleDTOs.add(convertFlightScheduleToFlightScheduleDTO(flightSchedule,convertCriteria));
		}
		
		return flightScheduleDTOs;

	}
	
	public FlightScheduleDTO convertFlightScheduleToFlightScheduleDTO(FlightSchedule flightSchedule, FlightScheduleConvertCriteriaDTO convertCriteria) {
		
		FlightScheduleDTO flightScheduleDTO = new FlightScheduleDTO();
		
		flightScheduleDTO.setFlightScheduleId(flightSchedule.getFlightScheduleId());

	
		flightScheduleDTO.setDepartureDate(flightSchedule.getDepartureDate());

	

		
		return flightScheduleDTO;
	}

	public ResultDTO updateFlightSchedule(FlightScheduleDTO flightScheduleDTO, RequestDTO requestDTO) {
		
		FlightSchedule flightSchedule = flightScheduleDao.getById(flightScheduleDTO.getFlightScheduleId());

		flightSchedule.setFlightScheduleId(ControllerUtils.setValue(flightSchedule.getFlightScheduleId(), flightScheduleDTO.getFlightScheduleId()));

		flightSchedule.setDepartureDate(ControllerUtils.setValue(flightSchedule.getDepartureDate(), flightScheduleDTO.getDepartureDate()));



        flightSchedule = flightScheduleDao.save(flightSchedule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FlightScheduleDTO getFlightScheduleDTOById(Integer flightScheduleId) {
	
		FlightSchedule flightSchedule = flightScheduleDao.getById(flightScheduleId);
			
		
		FlightScheduleConvertCriteriaDTO convertCriteria = new FlightScheduleConvertCriteriaDTO();
		return(this.convertFlightScheduleToFlightScheduleDTO(flightSchedule,convertCriteria));
	}







}
