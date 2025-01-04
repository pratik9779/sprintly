package com.sprintly.sprintly.service.organization;

import com.sprintly.sprintly.entity.Organization;
import com.sprintly.sprintly.model.Organization.OrganizationDeleteDto;
import com.sprintly.sprintly.model.Organization.OrganizationDto;
import com.sprintly.sprintly.model.Organization.UserOrganizationRoleDTO;

import java.util.List;

public interface OrganizationService {

    public Organization create(OrganizationDto organizationDto);

    public List<UserOrganizationRoleDTO> getAllUserOrganizationRole(String emailID);

    public String deleteOrganization(OrganizationDeleteDto name);
}
