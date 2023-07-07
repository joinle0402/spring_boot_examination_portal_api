package com.johnsmith.springbootstudentmanagementsystem.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterRequest {
    private String fullname;
    private String username;
    private String password;
    private Set<String> roles = new HashSet<>();
}
