package com.jdvpl.accounts.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "accountNumber cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "accountNumber should be valid")
    private Long accountNumber;

    @NotEmpty(message = "accountType cannot be empty")
    private String accountType;

    @NotEmpty(message = "branchName cannot be empty")
    private String branchAddres;
}
