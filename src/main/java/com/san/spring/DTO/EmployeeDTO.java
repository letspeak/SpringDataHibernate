package com.san.spring.DTO;


import java.time.ZonedDateTime;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import com.san.spring.model.Employee;

public class EmployeeDTO {
	
	private String createdByUser;
    private ZonedDateTime creationTime;
    private String modifiedByUser;
    private ZonedDateTime modificationTime;
    private Long id;
    @Size(max = Employee.MAX_LENGTH_DESCRIPTION)
    private String fName;
    @NotEmpty
    @Size(max = Employee.MAX_LENGTH_DESCRIPTION)
    private String lName;
	public String getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}
	public ZonedDateTime getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(ZonedDateTime creationTime) {
		this.creationTime = creationTime;
	}
	public String getModifiedByUser() {
		return modifiedByUser;
	}
	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}
	public ZonedDateTime getModificationTime() {
		return modificationTime;
	}
	public void setModificationTime(ZonedDateTime modificationTime) {
		this.modificationTime = modificationTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
    
}
