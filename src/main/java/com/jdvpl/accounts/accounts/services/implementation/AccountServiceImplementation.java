package com.jdvpl.accounts.accounts.services.implementation;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.jdvpl.accounts.accounts.dto.AccountsDto;
import com.jdvpl.accounts.accounts.entity.Accounts;
import com.jdvpl.accounts.accounts.mapper.AccountsMapper;
import com.jdvpl.accounts.accounts.repository.AccountsRepository;
import com.jdvpl.accounts.accounts.services.IAccountService;
import com.jdvpl.accounts.constants.AccountsConstants;
import com.jdvpl.accounts.customer.dto.CustomerDto;
import com.jdvpl.accounts.customer.entity.Customer;
import com.jdvpl.accounts.customer.exceptionHandler.CustomerAlreadyExistsException;
import com.jdvpl.accounts.customer.mapper.CustomerMapper;
import com.jdvpl.accounts.customer.repository.CustomerRepository;
import com.jdvpl.accounts.errorException.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImplementation implements IAccountService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobile(customerDto.getMobile());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer already exists with mobile number: " + customerDto.getMobile());
        }
        Customer customerSaved = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(customerSaved));
    }

    /**
     * Create new account.
     * 
     * @param customer
     * @return
     */

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 10000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddres(AccountsConstants.ADDRESS);
        return newAccount;

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobile(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile", mobileNumber));

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "CustomerId",
                        customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto
                .setAccountsDto(AccountsMapper.mapToAcccountsDto(account, new AccountsDto()));
        return customerDto;

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
                            accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("customer", "customerId", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobile(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
