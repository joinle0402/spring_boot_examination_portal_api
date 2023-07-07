package com.johnsmith.springbootstudentmanagementsystem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiException extends RuntimeException {
    private String message;
    private Integer status;
}
