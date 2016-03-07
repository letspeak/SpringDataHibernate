package com.san.spring.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2065896661252578764L;
	private final Long Id;

	public EmployeeNotFoundException(Long id) {
		super();
		Id = id;
	}

	public Long getId() {
		return Id;
	}
	
}
