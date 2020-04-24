package com.finalproject.demo.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode



@Entity
public class User implements UserDetails {


    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false, unique = true)
    private String username;


    @Column(nullable = false)
    private String password;


    @Transient
    private String passwordConfirm;


    @Email
    @Column(unique = true, nullable = false)
    private String email;


    @Pattern(regexp = "(\\+38|0)[0-9]{10}")
    @Column (unique = true, nullable = false)
    private String phoneNumber;


    @Column(nullable = false)
    private String country;


    @Column(nullable = false)
    private String language;


    private boolean enabled;

    @OneToOne
    private Viewer viewer;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Device> device = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private Set<Role> role = new HashSet<>();









    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }
}

