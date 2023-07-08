package com.johnsmith.examportal.api.controllers;

import com.johnsmith.examportal.api.payloads.requests.RoleRequest;
import com.johnsmith.examportal.api.payloads.responses.RoleResponse;
import com.johnsmith.examportal.api.services.interfaces.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.roleService.findAll().stream()
                        .map(role -> this.modelMapper.map(role, RoleResponse.class))
                        .collect(Collectors.toSet())
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RoleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.modelMapper.map(this.roleService.create(request), RoleResponse.class)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.roleService.delete(id));
    }
}
