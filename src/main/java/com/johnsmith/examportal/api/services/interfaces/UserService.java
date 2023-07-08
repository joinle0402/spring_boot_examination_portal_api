package com.johnsmith.examportal.api.services.interfaces;

import com.johnsmith.examportal.api.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    String delete(Long id);
}
