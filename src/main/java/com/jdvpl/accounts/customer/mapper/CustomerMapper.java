package com.jdvpl.accounts.customer.mapper;

import com.jdvpl.accounts.customer.dto.CustomerDto;
import com.jdvpl.accounts.customer.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobile(customer.getMobile());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobile(customerDto.getMobile());
        return customer;
    }
}
