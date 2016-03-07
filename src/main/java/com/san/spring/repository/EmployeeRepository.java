package com.san.spring.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import com.san.spring.model.Employee;

public interface EmployeeRepository extends Repository<Employee, Long> {

	// Basic Type Return Query
	@Query("SELECT e.firstName FROM Employee e where e.id = :id")
	public String findByFirstNameById(@Param("id") Long Id);

	@Query("SELECT e.lastName FROM Employee e where e.id = :id")
	public String findByLastNameById(@Param("id") Long Id);

	public long countByLastName(String lname);

	// Entity Types Return
	public Employee findOne(Long id);

	public List<Employee> findByLastName(String lname);

	public List<Employee> findByFirstName(String fname);

	@Query(value = "SELECT e FROM Employee e where e.firstName = :fname and e.lastName = :lname")
	public List<Employee> findByFirstNameAndLastName(@Param("fname") String fname, @Param("lname") String lname);

	// Option Type Return
	public Optional<Employee> findDistinctByLastName(String lname);

	// Async call
	@Async
	public Future<Employee> findById(Long id);

	@Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%',:searchTerm, '%')) "
			+ "OR LOWER(e.lastName) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
	public Page<Employee> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageRequest);
	
	//CRUD operations
	public void delete(Employee deleted);
	public void delete(Long Id);
	public List<Employee> findAll();
	public Employee save(Employee persisted);
	public void flush();
}
