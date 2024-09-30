package com.ninjaone.dundie_awards.controller;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.MessageBroker;
import com.ninjaone.dundie_awards.dto.EmployeeDTO;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import com.ninjaone.dundie_awards.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(
        name = "CRUD REST APIs for Employee Resource",
        description = "CRUD REST APIs - Create employee, Update employee, Get employee, Get all employees, Delete employee"
)
@Controller
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MessageBroker messageBroker;

    @Autowired
    private AwardsCache awardsCache;

    @Operation(
            summary = "Get all employees REST API",
            description = "Get all employees REST API is used to get all employees from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )
    @GetMapping("/employees")
    @ResponseBody
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Operation(
            summary = "Create employee REST API",
            description = "Create employee REST API is used to save an employee in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping("/employees")
    @ResponseBody
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        URI uriLocation = new UriTemplate(builder.toUriString() + "/{id}").expand(createdEmployee.getId());
        return ResponseEntity.created(uriLocation).body(createdEmployee);
    }

    // get employee by id rest api
    @Operation(
            summary = "Get employee by id REST API",
            description = "Get employee by id REST API is used to get a single employee from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )
    @GetMapping("/employees/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    @Operation(
            summary = "Update employee REST API",
            description = "Update User REST API is used to update an employee in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )
    @PutMapping("/employees/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDetails) {
        EmployeeDTO employeeDTO = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(employeeDTO);
    }

    @Operation(
            summary = "Delete employee REST API",
            description = "Delete employee REST API is used to delete an employee from the database"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS 200 SUCCESS",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    description = "Deleted response",
                                    type = "object",
                                    example = "{ \"deleted\": true }"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found",
                    content = @Content)
    })
    @DeleteMapping("/employees/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}