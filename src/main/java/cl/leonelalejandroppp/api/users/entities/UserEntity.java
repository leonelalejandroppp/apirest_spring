package cl.leonelalejandroppp.api.users.entities;

import cl.leonelalejandroppp.api.phones.entities.PhoneEntity;
import cl.leonelalejandroppp.api.users.dto.UserCreatedDTO;
import cl.leonelalejandroppp.api.users.dto.UserListDTO;
import cl.leonelalejandroppp.api.users.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String token;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserCreatedDTO toCreatedResponse() {
        return UserCreatedDTO.builder()
                .id(this.getId().toString())
                .created(this.getCreated())
                .modified(this.getModified())
                .lastLogin(this.getCreated())
                .token("")
                .isActive(this.isActive())
                .build();
    }

    public UserListDTO toListDTOResponse() {
        return UserListDTO.builder()
                .id(this.getId().toString())
                .email(this.getEmail())
                .name(this.getName())
                .phones(this.getPhones())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}