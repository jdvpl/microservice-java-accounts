package com.jdvpl.accounts.accounts.services;

import com.jdvpl.accounts.customer.dto.CustomerDto;

public interface IAccountService {
    /**
     * Create account.
     * 
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetch account.
     * 
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);
}
