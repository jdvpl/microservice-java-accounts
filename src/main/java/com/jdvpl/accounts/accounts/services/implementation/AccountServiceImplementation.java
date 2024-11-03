package com.jdvpl.accounts.accounts.services.implementation;

import org.springframework.stereotype.Service;

import com.jdvpl.accounts.accounts.repository.AccountsRepository;
import com.jdvpl.accounts.accounts.services.IAccountService;
import com.jdvpl.accounts.customer.dto.CustomerDto;
import com.jdvpl.accounts.customer.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImplementation implements IAccountService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

}
