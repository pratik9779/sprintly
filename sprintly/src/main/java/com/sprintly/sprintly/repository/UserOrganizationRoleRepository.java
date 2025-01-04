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
//    List<UserOrganizationRole> findByUserEmailID(String emailID);

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


//    boolean existsByUserEmailIDAndOrganizationNameAndRole(String emailID, String organizationName, OrganizationRole role);

    void deleteAllByOrganization(Organization organization);

    //    @Query("""
//            SELECT ur
//            FROM UserOrganizationRole ur
//            LEFT JOIN ur.organization o ON o.name = :organizationName
//            LEFT JOIN ur.user u ON u.emailID = :emailID
//            WHERE ur.role = :role
//            """)
    @Query("""
            SELECT ur
            FROM UserOrganizationRole ur
            JOIN FETCH ur.user u
            JOIN FETCH ur.organization o
            WHERE u.emailID = :emailID
              AND o.name = :organizationName
              AND ur.role = :role
            """)
    List<UserOrganizationRole> listByUserNameAndOrgNameAndUserRole(String emailID, String organizationName, OrganizationRole role);

}
