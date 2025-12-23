package com.example.Clinica.security.user;

import com.example.Clinica.common.audit.FullAuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser extends FullAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions = new HashSet<>();

    @Column(nullable = false)
    private boolean accountNoExpired;
    @Column(nullable = false)
    private boolean isEnabled;
    @Column(nullable = false)
    private boolean accountNoLocked;
    @Column(nullable = false)
    private boolean credentialsNoExpired;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        this.roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));

            role.getPermissions().forEach(p -> {
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            });
        });

        this.permissions.forEach(p -> {
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        });

        return authorities;
    }

}
