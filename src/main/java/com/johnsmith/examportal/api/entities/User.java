package com.johnsmith.examportal.api.entities;

import com.johnsmith.examportal.api.constants.TableConstant;
import com.johnsmith.examportal.api.payloads.responses.UserPrincipalResponse;
import com.johnsmith.examportal.api.payloads.responses.UserResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = TableConstant.TABLE_USERS, uniqueConstraints = {
        @UniqueConstraint(columnNames = { TableConstant.COLUMN_USERNAME })
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullname;

    @NaturalId(mutable = true)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @Lob
    private String avatar;

    private Boolean isEnabled = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = TableConstant.TABLE_USER_ROLES,
            joinColumns = @JoinColumn(name = TableConstant.COLUMN_USER_ID),
            inverseJoinColumns = @JoinColumn(name = TableConstant.COLUMN_ROLE_ID)
    )
    private Set<Role> roles = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = TableConstant.COLUMN_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = TableConstant.COLUMN_UPDATED_AT)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public UserResponse toUserResponse() {
        Set<String> roles = this.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        UserResponse userResponse = new UserResponse();
        userResponse.setFullname(this.getFullname());
        userResponse.setUsername(this.getUsername());
        userResponse.setRoles(roles);
        userResponse.setAvatar(this.getAvatar());
        userResponse.setCreatedAt(this.getCreatedAt());
        userResponse.setUpdatedAt(this.getUpdatedAt());
        return userResponse;
    }

}
