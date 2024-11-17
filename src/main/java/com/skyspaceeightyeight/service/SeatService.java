package com.skyspaceeightyeight.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeightyeight.domain.Seat;
import com.skyspaceeightyeight.dto.SeatDTO;
import com.skyspaceeightyeight.dto.SeatSearchDTO;
import com.skyspaceeightyeight.dto.SeatPageDTO;
import com.skyspaceeightyeight.dto.SeatConvertCriteriaDTO;
import com.skyspaceeightyeight.service.GenericService;
import com.skyspaceeightyeight.dto.common.RequestDTO;
import com.skyspaceeightyeight.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SeatService extends GenericService<Seat, Integer> {

	List<Seat> findAll();

	ResultDTO addSeat(SeatDTO seatDTO, RequestDTO requestDTO);

	ResultDTO updateSeat(SeatDTO seatDTO, RequestDTO requestDTO);

    Page<Seat> getAllSeats(Pageable pageable);

    Page<Seat> getAllSeats(Specification<Seat> spec, Pageable pageable);

	ResponseEntity<SeatPageDTO> getSeats(SeatSearchDTO seatSearchDTO);
	
	List<SeatDTO> convertSeatsToSeatDTOs(List<Seat> seats, SeatConvertCriteriaDTO convertCriteria);

	SeatDTO getSeatDTOById(Integer seatId);







}





