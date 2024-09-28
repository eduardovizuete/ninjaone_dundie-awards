package com.ninjaone.dundie_awards.dto;

public class EmployeeDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer dundieAwards;

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