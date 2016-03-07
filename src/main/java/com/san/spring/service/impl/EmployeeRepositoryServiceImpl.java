package com.san.spring.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.san.spring.DTO.EmployeeDTO;
import com.san.spring.model.Employee;
import com.san.spring.model.EmployeeMapper;
import com.san.spring.repository.EmployeeRepository;
import com.san.spring.service.EmployeeRepositoryService;

@Service
public class EmployeeRepositoryServiceImpl implements EmployeeRepositoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryServiceImpl.class);
	
	private final EmployeeRepository repository;
	
	@Autowired
	public EmployeeRepositoryServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public EmployeeDTO create(Employee newEmployeeEntry) {
		LOGGER.info("Creating new employee using {} " , newEmployeeEntry);
		Employee newEmployee = Employee.getBuilder()
				.fName(newEmployeeEntry.getFirstName())
				.lName(newEmployeeEntry.getLastName())
				.build();
		newEmployee = repository.save(newEmployee);
		LOGGER.info("Created new employee {} " , newEmployee);
		return EmployeeMapper.mapEntityIntoDTO(newEmployee);
	}

	@Transactional
	public EmployeeDTO delete(Long Id) {
		LOGGER.info("Deleting a employee with Id: {} " , Id);
		Employee deleted = findEmployeeEntryById(Id);
		repository.delete(deleted);
		LOGGER.info("Deleted employee {} " , deleted);
		return EmployeeMapper.mapEntityIntoDTO(deleted);
	}

	@Transactional(readOnly = true)
	public List<EmployeeDTO> findAll() {
		LOGGER.info("Finding all employee entries.");
		List<Employee> employeeEntries = repository.findAll();
		LOGGER.info("Found {} employee entries " , employeeEntries.size());
		return EmployeeMapper.mapEntitiesIntoDTOs(employeeEntries);
	}

	@Transactional(readOnly = true)
	public EmployeeDTO findById(Long Id) {
		LOGGER.info("Finding employee entry by id: {} " , Id);
		Employee employee = repository.findOne(Id); 
		LOGGER.info("Found employee entry : {} " , employee);
		return EmployeeMapper.mapEntityIntoDTO(employee);
	}

	public EmployeeDTO update(Employee updatedEmployee) {
		LOGGER.info("Updating employee : {} " , updatedEmployee);
		Employee updated = findEmployeeEntryById(updatedEmployee.getId());
        updated.update(updatedEmployee.getFirstName(), updatedEmployee.getLastName());
        repository.flush();
        LOGGER.info("Updated employee : {} " , updated);
        return EmployeeMapper.mapEntityIntoDTO(updated);
	}

	private Employee findEmployeeEntryById(Long id) {
        Employee employeeResult = repository.findOne(id);
        return employeeResult;
    }
}
