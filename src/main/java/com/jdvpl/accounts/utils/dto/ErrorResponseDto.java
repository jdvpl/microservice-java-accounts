package com.jdvpl.accounts.utils.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema
public class ErrorResponseDto {
    @Schema(description = "API path", example = "/account/create")
    private String apiPath;
    @Schema(description = "HTTP error code", example = "400")
    private HttpStatus errorCode;
    @Schema(description = "Error message", example = "Mobile should be valid")
    private String errorMessage;
    @Schema(description = "Error time", example = "2021-09-01T10:20:30")
    private LocalDateTime errorTime;
}
