package com.sprintly.sprintly.model.Organization;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganizationDeleteDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email ID is required")
    @Email
    private String emailID;
}
