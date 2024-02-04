package com.gil.school.api.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    private String message;

}
