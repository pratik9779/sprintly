package com.sprintly.sprintly.service.organization;

import com.sprintly.sprintly.entity.Organization;
import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.exception.custom.CustomException;
import com.sprintly.sprintly.model.Organization.OrganizationDto;
import com.sprintly.sprintly.repository.OrganizationRepository;
import com.sprintly.sprintly.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

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
                .user(currUser)
                .name(organizationDto.getName())
                .description(organizationDto.getDescription() == null ? "" : organizationDto.getDescription())
                .build();

        organizationRepository.save(organization);
        return organization;
    }

    @Override
    public List<Organization> getAllOrganizations(String emailID) {
        User currUser = userRepository
                .findByEmailID(emailID)
                .orElseThrow(() ->
                        new CustomException(String.format("No user with email '%s' found", emailID)));
        return currUser.getOrganizations();
    }
}
