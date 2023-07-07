package com.johnsmith.springbootstudentmanagementsystem.security;

import com.johnsmith.springbootstudentmanagementsystem.exceptions.ApiException;
import com.johnsmith.springbootstudentmanagementsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new ApiException("Unauthorized!", HttpStatus.UNAUTHORIZED.value()));
    }
}
