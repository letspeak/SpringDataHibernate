package com.san.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.san.spring.DTO.EmployeeDTO;
import com.san.spring.model.Employee;
import com.san.spring.model.EmployeeMapper;
import com.san.spring.repository.EmployeeRepository;
import com.san.spring.service.EmployeeSearchService;

@Service
public class EmployeeSearchServiceImpl implements EmployeeSearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSearchServiceImpl.class);

	private final EmployeeRepository repository;

	@Autowired
	public EmployeeSearchServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findBySearchTerm(String searchTerm, Pageable pageRequest) {
		LOGGER.info("Finding Employee entries by search term: {} and page request: {}", searchTerm, pageRequest);

		Page<Employee> searchResultPage = repository.findBySearchTerm(searchTerm, pageRequest);

        LOGGER.info("Found {} employee entries. Returned page {} contains {} todo entries",
                searchResultPage.getTotalElements(),
                searchResultPage.getNumber(),
                searchResultPage.getNumberOfElements()
        );
        return EmployeeMapper.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
	}

}
