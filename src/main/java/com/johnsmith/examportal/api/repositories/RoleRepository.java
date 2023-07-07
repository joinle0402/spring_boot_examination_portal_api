package com.johnsmith.springbootstudentmanagementsystem.repositories;

import com.johnsmith.springbootstudentmanagementsystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
    Set<Role> findAllByNameIn(Set<String> names);
}
