package com.san.spring.service;

import java.util.List;

import com.san.spring.DTO.EmployeeDTO;
import com.san.spring.model.Employee;

public interface EmployeeRepositoryService {

	public EmployeeDTO create(Employee employee );
	
	public EmployeeDTO delete(Long Id);
	
	public List<EmployeeDTO> findAll();
	
	public EmployeeDTO findById(Long Id);
	
	public EmployeeDTO update(Employee employee);
	
}
