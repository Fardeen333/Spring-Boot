package com.codingShuttleSpringWeb.SpringWebTutorial.dto;

import com.codingShuttleSpringWeb.SpringWebTutorial.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name is required while creating a user.")
    @Size(min = 5, max = 10, message = "Size of the string not applicable")
    private String name;

    @NotNull(message = "Email is Required...")
    @Email(message = "Email should be valid....")
    private String email;


    @NotNull(message = "Age is required...")
    @Max(value = 34, message = "Age should be less than 34")
    @Min(value = 18, message = "Age should not be less than 18")
    private Integer age;

    @NotNull
    @EmployeeRoleValidation
    private String role;

    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;


}
