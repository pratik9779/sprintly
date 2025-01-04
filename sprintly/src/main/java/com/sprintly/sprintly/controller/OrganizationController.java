package com.sprintly.sprintly.controller;

import com.sprintly.sprintly.entity.Organization;
import com.sprintly.sprintly.exception.custom.CustomException;
import com.sprintly.sprintly.model.Organization.OrganizationDeleteDto;
import com.sprintly.sprintly.model.Organization.OrganizationDto;
import com.sprintly.sprintly.model.Organization.UserOrganizationRoleDTO;
import com.sprintly.sprintly.service.organization.OrganizationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
@Log4j2
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/create")
    public Organization createOrganization(@RequestBody OrganizationDto organizationDto) {
        return organizationService.create(organizationDto);
    }

    @PostMapping("/all")
    public List<UserOrganizationRoleDTO> getAllUserOrganizationRole(@RequestParam String emailID,
                                                                    Authentication authentication) {

        String jwtEmail = authentication.getName();

        if (!jwtEmail.equals(emailID)) {
            throw new CustomException("Email in JWT does not match email in request.");
        }
        return organizationService.getAllUserOrganizationRole(emailID);
    }

    @DeleteMapping("/delete")
    public String deleteOrganization(
            @RequestBody OrganizationDeleteDto organizationDeleteDto,
            Authentication authentication) {

        String jwtEmail = authentication.getName();
        String dtoEmail = organizationDeleteDto.getEmailID();

        if (!jwtEmail.equals(dtoEmail)) {
            throw new CustomException("Email in JWT does not match email in request.");
        }

        return organizationService.deleteOrganization(organizationDeleteDto);
    }

}