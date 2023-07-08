package com.johnsmith.examportal.api.services.implementations;

import com.johnsmith.examportal.api.services.interfaces.UserService;
import com.johnsmith.examportal.api.entities.User;
import com.johnsmith.examportal.api.exceptions.ApiException;
import com.johnsmith.examportal.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public String delete(Long id) {
        User userToDelete = this.userRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found!"));
        this.userRepository.delete(userToDelete);
        return "Deleted User!";
    }
}
