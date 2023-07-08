package com.johnsmith.examportal.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiException extends RuntimeException {
    private Integer status;
    private String message;

    public ApiException(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
