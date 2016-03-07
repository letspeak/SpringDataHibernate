package com.san.spring.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "tbl_employee")
public final class Employee {

	public static final int MAX_LENGTH_DESCRIPTION = 30;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "fname", nullable = false)
	private String firstName;
	@Column(name = "lname", nullable = false)
	private String lastName;

	@Column(name="created_by",nullable=false)
	@CreatedBy
	private String createdByUser;
	
	@Column(name="created_at",nullable=false)
	@CreatedDate
    private ZonedDateTime creationTime;
	@Column(name="modified_by",nullable=false)
	@LastModifiedBy
    private String modifiedByUser;
	@Column(name="modified_at",nullable=false)
	@LastModifiedDate
    private ZonedDateTime modificationTime;
    
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

	public Employee() {

	}

	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	private Employee(Builder builder) {
        this.firstName = builder.fName;
        this.lastName = builder.lName;
    }

    public static Builder getBuilder() {
        return new Builder();
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static class Builder {
        private String fName;
        private String lName;
        private Builder() {}

        public Builder fName(String fName) {
            this.fName = fName;
            return this;
        }
        public Builder lName(String lName) {
            this.lName = lName;
            return this;
        }
        public Employee build() {
        	Employee build = new Employee(this);
            return build;
        }
    }

	public void update(String newFirstName, String newLastName) {
		this.firstName = newFirstName;
		this.lastName = newLastName;
	}
}
