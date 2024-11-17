package com.skyspaceeightyeight.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspaceeightyeight.domain.LoungeAccess;
import com.skyspaceeightyeight.dto.LoungeAccessDTO;
import com.skyspaceeightyeight.dto.LoungeAccessSearchDTO;
import com.skyspaceeightyeight.dto.LoungeAccessPageDTO;
import com.skyspaceeightyeight.dto.LoungeAccessConvertCriteriaDTO;
import com.skyspaceeightyeight.service.GenericService;
import com.skyspaceeightyeight.dto.common.RequestDTO;
import com.skyspaceeightyeight.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoungeAccessService extends GenericService<LoungeAccess, Integer> {

	List<LoungeAccess> findAll();

	ResultDTO addLoungeAccess(LoungeAccessDTO loungeAccessDTO, RequestDTO requestDTO);

	ResultDTO updateLoungeAccess(LoungeAccessDTO loungeAccessDTO, RequestDTO requestDTO);

    Page<LoungeAccess> getAllLoungeAccesss(Pageable pageable);

    Page<LoungeAccess> getAllLoungeAccesss(Specification<LoungeAccess> spec, Pageable pageable);

	ResponseEntity<LoungeAccessPageDTO> getLoungeAccesss(LoungeAccessSearchDTO loungeAccessSearchDTO);
	
	List<LoungeAccessDTO> convertLoungeAccesssToLoungeAccessDTOs(List<LoungeAccess> loungeAccesss, LoungeAccessConvertCriteriaDTO convertCriteria);

	LoungeAccessDTO getLoungeAccessDTOById(Integer loungeAccessId);







}





