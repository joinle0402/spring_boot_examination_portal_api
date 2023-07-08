package com.johnsmith.examportal.api.services.implementations;

import com.johnsmith.examportal.api.entities.Role;
import com.johnsmith.examportal.api.exceptions.ApiException;
import com.johnsmith.examportal.api.payloads.requests.RoleRequest;
import com.johnsmith.examportal.api.repositories.RoleRepository;
import com.johnsmith.examportal.api.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role create(RoleRequest request) {
        Optional<Role> role = this.roleRepository.findByName(request.getName());
        if (role.isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT, "Role already exists!");
        }

        Role roleToCreate = Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return this.roleRepository.save(roleToCreate);
    }

    @Override
    public String delete(Integer id) {
        Role roleToDelete = this.roleRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found!"));
        this.roleRepository.delete(roleToDelete);
        return "Deleted role!";
    }
}
