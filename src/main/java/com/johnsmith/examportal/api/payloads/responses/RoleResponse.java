package com.johnsmith.springbootstudentmanagementsystem.payloads.responses;

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
public class RoleResponse {
    private Integer id;
    private String name;
    private String description;
}
