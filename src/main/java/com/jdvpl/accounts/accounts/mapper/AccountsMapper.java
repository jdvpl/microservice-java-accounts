package com.jdvpl.accounts.accounts.mapper;

import com.jdvpl.accounts.accounts.dto.AccountsDto;
import com.jdvpl.accounts.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto mapToAcccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumer());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddres(accounts.getBranchAddres());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumer(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddres(accountsDto.getBranchAddres());
        return accounts;
    }
}
