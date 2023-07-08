package com.johnsmith.examportal.api.services.interfaces;

import com.johnsmith.examportal.api.entities.Role;
import com.johnsmith.examportal.api.payloads.requests.RoleRequest;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role create(RoleRequest request);
    String delete(Integer id);
}
