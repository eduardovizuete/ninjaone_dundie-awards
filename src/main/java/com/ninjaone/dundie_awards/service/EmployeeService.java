package com.ninjaone.dundie_awards.service;

import com.ninjaone.dundie_awards.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    Optional<EmployeeDTO> getEmployeeById(Long id);

    EmployeeDTO createEmployee(EmployeeDTO dto);

    Optional<EmployeeDTO> updateEmployee(Long id, EmployeeDTO dto);

    Optional<EmployeeDTO> deleteEmployee(Long id);

}
