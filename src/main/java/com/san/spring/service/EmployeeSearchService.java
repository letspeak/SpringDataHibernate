package com.san.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.san.spring.DTO.EmployeeDTO;

public interface EmployeeSearchService {

	public Page<EmployeeDTO> findBySearchTerm(@Param("searchTerm") String searchTerm,Pageable pageRequest);
}
