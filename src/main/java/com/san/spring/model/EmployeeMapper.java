package com.san.spring.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.san.spring.DTO.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public final class EmployeeMapper {

	public static List<EmployeeDTO> mapEntitiesIntoDTOs(Iterable<Employee> entities) {
        List<EmployeeDTO> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(mapEntityIntoDTO(e)));
        return dtos;
    }
	
    public static EmployeeDTO mapEntityIntoDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setCreatedByUser(entity.getCreatedByUser());
        dto.setCreationTime(entity.getCreationTime());
        dto.setfName(entity.getFirstName());
        dto.setId(entity.getId());
        dto.setModifiedByUser(entity.getModifiedByUser());
        dto.setModificationTime(entity.getModificationTime());
        dto.setlName(entity.getLastName());
        return dto;
    }
    
    public static Page<EmployeeDTO> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<Employee> source) {
        List<EmployeeDTO> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<EmployeeDTO>(dtos, pageRequest, source.getTotalElements());
    }
}