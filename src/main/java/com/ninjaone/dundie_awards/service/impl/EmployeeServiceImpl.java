package com.ninjaone.dundie_awards.service.impl;

import com.ninjaone.dundie_awards.dto.EmployeeDTO;
import com.ninjaone.dundie_awards.dto.OrganizationDTO;
import com.ninjaone.dundie_awards.mapper.EmployeeMapper;
import com.ninjaone.dundie_awards.mapper.OrganizationMapper;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    private OrganizationMapper organizationMapper = Mappers.getMapper(OrganizationMapper.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toEmployeeDTOList(employees);
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return Optional.ofNullable(employeeMapper.toEmployeeDTO(employee.get()));
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee createdEmployee = employeeRepository.save(employeeMapper.toEmployee(dto));
        return employeeMapper.toEmployeeDTO(createdEmployee);
    }

    @Override
    public Optional<EmployeeDTO> updateEmployee(Long id, EmployeeDTO dto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return Optional.empty();
        }

        Employee employee = optionalEmployee.get();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDundieAwards(dto.getDundieAwards());
        employee.setOrganization(organizationMapper.toOrganization(dto.getOrganization()));

        Employee updatedEmployee = employeeRepository.save(employee);
        return Optional.ofNullable(employeeMapper.toEmployeeDTO(updatedEmployee));
    }

    @Override
    public Optional<EmployeeDTO> deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return Optional.empty();
        }

        Employee employee = optionalEmployee.get();
        employeeRepository.delete(employee);
        return Optional.ofNullable(employeeMapper.toEmployeeDTO(employee));
    }

}
