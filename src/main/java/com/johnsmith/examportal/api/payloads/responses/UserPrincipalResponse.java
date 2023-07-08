package com.johnsmith.examportal.api.payloads.responses;

import com.johnsmith.examportal.api.security.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserPrincipalResponse {
    private String fullname;
    private String username;
    private String avatar;
    private Set<String> roles;

    public static UserPrincipalResponse of(UserPrincipal userPrincipal) {
        Set<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return UserPrincipalResponse.builder()
                .fullname(userPrincipal.getUsername())
                .username(userPrincipal.getUsername())
                .avatar(userPrincipal.getAvatar())
                .roles(roles)
                .build();
    }
}
