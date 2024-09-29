package com.ninjaone.dundie_awards.mapper;

import com.ninjaone.dundie_awards.dto.OrganizationDTO;
import com.ninjaone.dundie_awards.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper MAPPER = Mappers.getMapper(OrganizationMapper.class);

    Organization toOrganization(OrganizationDTO dto);

    OrganizationDTO toOrganizationDTO(Organization organization);

    List<Organization> toOrganizationList(List<OrganizationDTO> organizationsDTO);

    List<OrganizationDTO> toOrganizationDTOList(List<Organization> organizations);
    
}
