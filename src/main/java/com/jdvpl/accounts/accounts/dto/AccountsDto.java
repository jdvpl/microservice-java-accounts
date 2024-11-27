package com.jdvpl.accounts.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "This class is responsible for holding account details")
public class AccountsDto {

    @Schema(description = "Account number of the customer", example = "1234567890")
    @NotEmpty(message = "accountNumber cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "accountNumber should be valid")
    private Long accountNumber;

    @Schema(description = "Account type of the customer", example = "Savings")
    @NotEmpty(message = "accountType cannot be empty")
    private String accountType;

    @Schema(description = "Branch address of the customer", example = "Bangalore")
    @NotEmpty(message = "branchName cannot be empty")
    private String branchAddres;
}
