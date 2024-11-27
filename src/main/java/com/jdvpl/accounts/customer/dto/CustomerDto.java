package com.jdvpl.accounts.customer.dto;

import com.jdvpl.accounts.accounts.dto.AccountsDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "This class is responsible for holding customer details")
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "Juan Daniel Suarez")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 50, message = "Name should be between 5 and 50 characters")
    private String name;

    @Schema(description = "Email of the customer", example = "jdvplsuarez@gmail.com")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Mobile of the customer", example = "9876543210")
    @NotEmpty(message = "Mobile cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be valid")
    private String mobile;

    @Schema(description = "Account details of the customer")
    private AccountsDto accountsDto;
}
