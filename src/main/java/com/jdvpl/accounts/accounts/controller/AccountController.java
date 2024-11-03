package com.jdvpl.accounts.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdvpl.accounts.constants.AccountsConstants;
import com.jdvpl.accounts.customer.dto.CustomerDto;
import com.jdvpl.accounts.utils.dto.ResponseDto;

@RestController
public class AccountController {

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.MESSAGE_201, AccountsConstants.MESSAGE_201));
    }
}
