package com.sprintly.sprintly.service.organization;

import com.sprintly.sprintly.entity.Organization;
import com.sprintly.sprintly.model.Organization.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    public Organization create(OrganizationDto organizationDto);

    public List<Organization> getAllOrganizations(String emailID);
}
