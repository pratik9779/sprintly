package com.sprintly.sprintly.service.security;


import com.sprintly.sprintly.model.enums.OrganizationRole;
import com.sprintly.sprintly.repository.UserOrganizationRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityService {

    @Autowired
    private UserOrganizationRoleRepository userOrganizationRoleRepository;

    /*
    SELECT CASE WHEN COUNT(uor) > 0 THEN TRUE ELSE FALSE
    FROM UserOrganizationRole uor
    WHERE uor.user.emailID = :emailID
      AND uor.organization.name = :organizationName
      AND uor.role = :role
     */
    public boolean hasOwnerRole(String emailID, String organizationName) {

        return userOrganizationRoleRepository.existsByUserEmailIDAndOrganizationNameAndRole(
                emailID, organizationName, OrganizationRole.OWNER
        );
    }

}