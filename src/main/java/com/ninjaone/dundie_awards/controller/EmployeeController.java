package com.ninjaone.dundie_awards.controller;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.MessageBroker;
import com.ninjaone.dundie_awards.dto.EmployeeDTO;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import com.ninjaone.dundie_awards.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping()
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MessageBroker messageBroker;

    @Autowired
    private AwardsCache awardsCache;

    // get all employees
    @GetMapping("/employees")
    @ResponseBody
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // create employee rest api
    @PostMapping("/employees")
    @ResponseBody
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    // get employee by id rest api
    @GetMapping("/employees/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDetails) {
        Optional<EmployeeDTO> employeeDTO = employeeService.updateEmployee(id, employeeDetails);
        return employeeDTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Optional<EmployeeDTO> optionalEmployee = employeeService.deleteEmployee(id);
        if (optionalEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }

    }
}