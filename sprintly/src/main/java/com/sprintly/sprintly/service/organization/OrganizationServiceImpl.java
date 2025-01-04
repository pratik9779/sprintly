package com.sprintly.sprintly.service.organization;

import com.sprintly.sprintly.entity.Organization;
import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.entity.UserOrganizationRole;
import com.sprintly.sprintly.exception.custom.CustomException;
import com.sprintly.sprintly.model.Organization.OrganizationDeleteDto;
import com.sprintly.sprintly.model.Organization.OrganizationDto;
import com.sprintly.sprintly.model.enums.OrganizationRole;
import com.sprintly.sprintly.repository.OrganizationRepository;
import com.sprintly.sprintly.repository.UserOrganizationRoleRepository;
import com.sprintly.sprintly.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOrganizationRoleRepository userOrganizationRoleRepository;

    @Override
    @Transactional
    // Without @Transactional, if one save operation fails, the other might still persist, causing partial updates.
    public Organization create(OrganizationDto organizationDto) {

        User currUser = userRepository
                .findByEmailID(organizationDto.getEmailID())
                .orElseThrow(() ->
                        new CustomException(String.format("No user with email '%s' found", organizationDto.getEmailID())));


        Organization organization = Organization
                .builder()
                .createdDate(LocalDateTime.now())
                .name(organizationDto.getName())
                .description(organizationDto.getDescription() == null ? "" : organizationDto.getDescription())
                .build();

        organizationRepository.save(organization);

        UserOrganizationRole userOrganizationRole =
                UserOrganizationRole
                        .builder()
                        .user(currUser)
                        .organization(organization)
                        .role(OrganizationRole.OWNER) // whoever is creating org is owner
                        .assignedDate(LocalDateTime.now())
                        .build();
        userOrganizationRoleRepository.save(userOrganizationRole);
        return organization;
    }

    @Override
    public List<UserOrganizationRole> getAllUserOrganizationRole(String emailID) {
        User currUser = userRepository
                .findByEmailID(emailID)
                .orElseThrow(() ->
                        new CustomException(String.format("No user with email '%s' found", emailID)));
        return currUser.getOrganizationRoles();
    }

    @Override
    @Transactional
    @PreAuthorize("@securityService.hasOwnerRole(#dto.emailID, #dto.name)")
    public String deleteOrganization(OrganizationDeleteDto dto) {
        Organization organization = organizationRepository.findByName(dto.getName())
                .orElseThrow(() -> new IllegalArgumentException("Organization not found"));

        organizationRepository.delete(organization);

        // Cascade delete roles associated with the organization
        userOrganizationRoleRepository.deleteAllByOrganization(organization);

        log.info("Organization successfully deleted");
        return String.format("Successfully deleted Organization %s", organization.getName());

    }
}
