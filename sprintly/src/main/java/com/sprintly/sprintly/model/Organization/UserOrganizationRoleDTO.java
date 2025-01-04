package com.sprintly.sprintly.model.Organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrganizationRoleDTO {
    private String userEmail;
    private String organizationName;
    private String role;
}
