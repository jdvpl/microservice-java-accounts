package com.jdvpl.accounts.customer.dto;

import com.jdvpl.accounts.accounts.dto.AccountsDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 50, message = "Name should be between 5 and 50 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Mobile cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be valid")
    private String mobile;

    private AccountsDto accountsDto;
}
