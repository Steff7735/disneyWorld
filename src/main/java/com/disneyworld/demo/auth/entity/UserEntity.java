package com.disneyworld.demo.auth.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Email
    private String username;
    @Size(min = 5)
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enable;

    public UserEntity(){
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enable = true;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public boolean isEnabled() {
        return false;
    }
}
