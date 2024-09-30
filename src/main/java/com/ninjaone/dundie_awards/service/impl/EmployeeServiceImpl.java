package com.ninjaone.dundie_awards.service.impl;

import com.ninjaone.dundie_awards.dto.EmployeeDTO;
import com.ninjaone.dundie_awards.exception.ResourceNotFoundException;
import com.ninjaone.dundie_awards.mapper.EmployeeMapper;
import com.ninjaone.dundie_awards.mapper.OrganizationMapper;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.model.Organization;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import com.ninjaone.dundie_awards.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    private OrganizationMapper organizationMapper = Mappers.getMapper(OrganizationMapper.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toEmployeeDTOList(employees);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee
                .map(employee -> employeeMapper.toEmployeeDTO(employee))
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee createdEmployee = employeeRepository.save(employeeMapper.toEmployee(dto));
        return employeeMapper.toEmployeeDTO(createdEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Employee", "id", id)
        );
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDundieAwards(dto.getDundieAwards());

        Organization org = organizationRepository.findById(dto.getOrganization().getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Organization", "id", dto.getOrganization().getId())
                );

        employee.setOrganization(organizationMapper.toOrganization(dto.getOrganization()));

        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );
        employeeRepository.delete(employee);
    }

}
