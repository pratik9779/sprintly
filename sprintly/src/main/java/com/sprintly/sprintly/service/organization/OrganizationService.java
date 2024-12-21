package com.sprintly.sprintly.service.organization;

import com.sprintly.sprintly.entity.Organization;
import com.sprintly.sprintly.entity.UserOrganizationRole;
import com.sprintly.sprintly.model.Organization.OrganizationDeleteDto;
import com.sprintly.sprintly.model.Organization.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    public Organization create(OrganizationDto organizationDto);

    public List<UserOrganizationRole> getAllUserOrganizationRole(String emailID);

    public void deleteOrganization(OrganizationDeleteDto name);
}
