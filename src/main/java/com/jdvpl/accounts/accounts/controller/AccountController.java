package com.jdvpl.accounts.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdvpl.accounts.accounts.services.IAccountService;
import com.jdvpl.accounts.constants.AccountsConstants;
import com.jdvpl.accounts.customer.dto.CustomerDto;
import com.jdvpl.accounts.utils.dto.ErrorResponseDto;
import com.jdvpl.accounts.utils.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/account", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
@Tag(name = "Crud Rest APIs for accounts", description = "This controller is responsible for handling account related operations")
public class AccountController {

        private final IAccountService accountService;

        @PostMapping("/create")
        @Operation(summary = "Create account", description = "This API is responsible for creating account & create new user")
        @ApiResponse(responseCode = "201", description = "Account created successfully")
        public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
                accountService.createAccount(customerDto);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
        }

        @GetMapping("/fetch")
        @Operation(summary = "Fetch account details", description = "This API is responsible for fetching account details")
        @ApiResponse(responseCode = "200", description = "Account details fetched successfully")
        public ResponseEntity<CustomerDto> fetchgAccountDewtails(
                        @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be valid") String mobileNumber) {
                CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
                return ResponseEntity.status(HttpStatus.OK).body(customerDto);
        }

        @PutMapping("/update")
        @Operation(summary = "Update account details", description = "This API is responsible for updating account details")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Account details updated successfully"),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
                boolean isUpdated = accountService.updateAccount(customerDto);
                if (isUpdated) {
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(new ResponseDto(AccountsConstants.STATUS_200,
                                                        AccountsConstants.MESSAGE_200));
                }
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }

        @DeleteMapping("/delete")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
                        @ApiResponse(responseCode = "500", description = "Internal server error")
        })
        @Operation(summary = "Delete account details & customer ", description = "This API is responsible for deleting account details")
        public ResponseEntity<ResponseDto> deleteAccountDetails(
                        @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be valid") String mobileNumber) {
                boolean isDeleted = accountService.deleteAccount(mobileNumber);
                if (isDeleted) {
                        return ResponseEntity.status(HttpStatus.OK)
                                        .body(new ResponseDto(AccountsConstants.STATUS_200,
                                                        AccountsConstants.MESSAGE_200));
                }
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }

}
