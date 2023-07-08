package com.johnsmith.examportal.api.services.implementations;

import com.johnsmith.examportal.api.entities.Role;
import com.johnsmith.examportal.api.entities.User;
import com.johnsmith.examportal.api.exceptions.ApiException;
import com.johnsmith.examportal.api.payloads.requests.LoginRequest;
import com.johnsmith.examportal.api.payloads.requests.RegisterRequest;
import com.johnsmith.examportal.api.payloads.responses.LoginResponse;
import com.johnsmith.examportal.api.payloads.responses.UserPrincipalResponse;
import com.johnsmith.examportal.api.repositories.RoleRepository;
import com.johnsmith.examportal.api.repositories.UserRepository;
import com.johnsmith.examportal.api.security.JwtUtils;
import com.johnsmith.examportal.api.security.UserPrincipal;
import com.johnsmith.examportal.api.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = this.jwtUtils.generateToken(userPrincipal);
        return LoginResponse.builder()
                .user(
                        UserPrincipalResponse.builder()
                                .fullname(userPrincipal.getFullname())
                                .username(userPrincipal.getUsername())
                                .avatar(userPrincipal.getAvatar())
                                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                                .build()
                )
                .accessToken(accessToken)
                .build();
    }

    @Override
    public User register(RegisterRequest request) {
        System.out.println("request.getUsername(): " + request.getUsername());
        Optional<User> user = this.userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT, "User already exists!");
        }

        Set<String> roleNamesDatabase = this.roleRepository.findAll().stream().map(Role::getName).map(String::toUpperCase).collect(Collectors.toSet());
        Set<String> roleNamesInput = request.getRoles().stream().map(String::toUpperCase).collect(Collectors.toSet());
        for (String roleName: roleNamesInput) {
            if (!roleNamesDatabase.contains(roleName)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Role is invalid");
            }
        }

        Set<Role> roles = this.roleRepository.findAllByNameIn(roleNamesInput);
        User userToCreate = new User();
        userToCreate.setFullname(request.getFullname());
        userToCreate.setUsername(request.getUsername());
        userToCreate.setPassword(this.passwordEncoder.encode(request.getPassword()));
        userToCreate.setRoles(roles);
        userToCreate.setCreatedAt(LocalDateTime.now());
        return this.userRepository.save(userToCreate);
    }
}
