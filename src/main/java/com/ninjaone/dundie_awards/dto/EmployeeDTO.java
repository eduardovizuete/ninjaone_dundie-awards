package com.ninjaone.dundie_awards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(
        description = "EmployeeDTO Model information"
)
public class EmployeeDTO {

    @Schema(
            description = "Employee Id"
    )
    private Long id;

    @Schema(
            description = "Employee First Name"
    )
    @NotEmpty(message = "Employee first name should not be null or empty")
    private String firstName;

    @Schema(
            description = "Employee Last Name"
    )
    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    @Schema(
            description = "Employee dundie Awards"
    )
    private Integer dundieAwards;

    @Schema(
            description = "Employee organization"
    )
    private OrganizationDTO organization;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String firstName, String lastName, Integer dundieAwards, OrganizationDTO organization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dundieAwards = dundieAwards;
        this.organization = organization;
    }

    public EmployeeDTO(String firstName, String lastName, OrganizationDTO organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
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

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public void setDundieAwards(int dundieAwards){
        this.dundieAwards = dundieAwards;
    }

    public Integer getDundieAwards(){
        return dundieAwards;
    }

}