package com.sprintly.sprintly.service.security;


import com.sprintly.sprintly.entity.UserOrganizationRole;
import com.sprintly.sprintly.model.enums.OrganizationRole;
import com.sprintly.sprintly.repository.UserOrganizationRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Transactional
    public boolean hasOwnerRole(String emailID, String organizationName) {
        List<UserOrganizationRole> list = userOrganizationRoleRepository.listByUserNameAndOrgNameAndUserRole(emailID, organizationName, OrganizationRole.OWNER);
        System.out.println(list.size());
        return !list.isEmpty();
    }

}