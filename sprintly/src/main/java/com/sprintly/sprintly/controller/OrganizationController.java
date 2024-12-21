package com.sprintly.sprintly.controller;

import com.sprintly.sprintly.entity.Organization;
import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.entity.UserOrganizationRole;
import com.sprintly.sprintly.model.Organization.OrganizationDeleteDto;
import com.sprintly.sprintly.model.Organization.OrganizationDto;
import com.sprintly.sprintly.model.auth.RegisterUserDto;
import com.sprintly.sprintly.service.organization.OrganizationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
@Log4j2
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/create")
    public Organization registerUser(@RequestBody OrganizationDto organizationDto) {
        return organizationService.create(organizationDto);
    }

    @PostMapping("/all")
    public List<UserOrganizationRole> getAllUserOrganizationRole(@RequestParam String emailID){
        return organizationService.getAllUserOrganizationRole(emailID);
    }

    @DeleteMapping("/delete/name")
    public void deleteOrganization(@RequestBody OrganizationDeleteDto organizationDeleteDto){
        organizationService.deleteOrganization(organizationDeleteDto);
    }

}
