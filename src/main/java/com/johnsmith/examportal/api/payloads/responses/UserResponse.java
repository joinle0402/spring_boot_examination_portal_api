package com.johnsmith.examportal.api.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponse {
    private String fullname;
    private String username;
    private Set<String> roles;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
