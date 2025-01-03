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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Transactional // Without @Transactional, if one save operation fails, the other might still persist, causing partial updates.
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

//    @Override
//    @Transactional
//    public void deleteOrganization(OrganizationDeleteDto dto) {
//        String orgName = dto.getName();
//        String userEmail = dto.getEmailID();
//        List<UserOrganizationRole> list = userOrganizationRoleRepository.findByUserEmailID(userEmail);
//
//        for (UserOrganizationRole uor : list) {
//            if (uor.getOrganization().getName().equals(orgName) && uor.getRole() == OrganizationRole.OWNER) {
//                // Delete the organization
//                organizationRepository.delete(uor.getOrganization());
//                log.info("Organization successfully deleted");
//
//                // Delete all associated roles for the organization
//                userOrganizationRoleRepository.deleteAll(
//                        userOrganizationRoleRepository.findByOrganization(uor.getOrganization())
//                );
//
//                return; // Exit the loop after successful deletion
//            }
//        }
//        log.info("You are not the owner or organization does not exist");
//    }

    @Override
    @Transactional
    @PreAuthorize("@securityService.hasOwnerRole(#dto.emailID, #dto.name)")
    public void deleteOrganization(OrganizationDeleteDto dto) {
        Organization organization = organizationRepository.findByName(dto.getName())
                .orElseThrow(() -> new IllegalArgumentException("Organization not found"));

        organizationRepository.delete(organization);

        // Cascade delete roles associated with the organization
        userOrganizationRoleRepository.deleteAllByOrganization(organization);

        log.info("Organization successfully deleted");
    }
}
