package com.ninjaone.dundie_awards.mapper;

import com.ninjaone.dundie_awards.dto.EmployeeDTO;
import com.ninjaone.dundie_awards.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    Employee toEmployee(EmployeeDTO dto);

    EmployeeDTO toEmployeeDTO(Employee employee);

    List<Employee> toEmployeeList(List<EmployeeDTO> employeesDTO);

    List<EmployeeDTO> toEmployeeDTOList(List<Employee> employees);

}
