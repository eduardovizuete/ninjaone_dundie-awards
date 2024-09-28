package com.ninjaone.dundie_awards.dto;

public class OrganizationDTO {

  private Long id;

  private String name;

  public OrganizationDTO() {
  }

  public OrganizationDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public OrganizationDTO(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
