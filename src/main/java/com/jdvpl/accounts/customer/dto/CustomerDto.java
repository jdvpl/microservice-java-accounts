package com.jdvpl.accounts.customer.dto;

import com.jdvpl.accounts.accounts.dto.AccountsDto;

import lombok.Data;

@Data
public class CustomerDto {
    private String name;
    private String email;
    private String mobile;

    private AccountsDto accountsDto;
}
