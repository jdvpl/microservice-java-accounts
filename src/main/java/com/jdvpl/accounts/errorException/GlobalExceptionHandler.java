package com.jdvpl.accounts.errorException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jdvpl.accounts.customer.exceptionHandler.CustomerAlreadyExistsException;
import com.jdvpl.accounts.utils.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                Map<String, String> validationErros = new HashMap<>();
                List<ObjectError> validarionErrorList = ex.getBindingResult().getAllErrors();
                validarionErrorList.forEach(error -> {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        validationErros.put(fieldName, errorMessage);
                });

                return new ResponseEntity<>(validationErros, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex,
                        WebRequest webRequest) {
                ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                                webRequest.getDescription(false),
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,
                        WebRequest webRequest) {
                ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                                webRequest.getDescription(false),
                                HttpStatus.NOT_FOUND,
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(CustomerAlreadyExistsException.class)
        public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex,
                        WebRequest webRequest) {
                ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                                webRequest.getDescription(false),
                                HttpStatus.BAD_REQUEST,
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
        }
}
