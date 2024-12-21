package com.sprintly.sprintly.repository;

import com.sprintly.sprintly.entity.Organization;
import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.entity.UserOrganizationRole;
import com.sprintly.sprintly.model.enums.OrganizationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserOrganizationRoleRepository extends JpaRepository<UserOrganizationRole, Long> {

    List<UserOrganizationRole> findByUser(User user);

    // Find roles by User's email ID
    @Query("SELECT uor FROM UserOrganizationRole uor WHERE uor.user.emailID = :email")
    List<UserOrganizationRole> findByUserEmailID(@Param("email") String email);

    // Find all roles by organization ID
    List<UserOrganizationRole> findByOrganization(Organization organization);

    // Check if a specific user has a specific role in an organization
    @Query("SELECT COUNT(uor) > 0 FROM UserOrganizationRole uor WHERE uor.user = :user AND uor.organization = :organization AND uor.role = :role")
    boolean existsByUserAndOrganizationAndRole(@Param("user") User user, @Param("organization") Organization organization, @Param("role") OrganizationRole role);


    // Optional: Find roles for a specific user and organization
    List<UserOrganizationRole> findByUserAndOrganization(User user, Organization organization);
}
