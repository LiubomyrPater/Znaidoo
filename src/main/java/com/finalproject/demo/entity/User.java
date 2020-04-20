package com.finalproject.demo.entity;

import com.finalproject.demo.entity.enumerations.Country;
import com.finalproject.demo.entity.enumerations.Language;
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
@Table(name = "user")
public class User implements UserDetails {


    @Id
    @GeneratedValue
    private Long id;


    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Transient
    private String passwordConfirm;


    @Email
    @Column(unique = true, nullable = false)
    private String email;


    @Pattern(regexp = "(\\+38|0)[0-9]{10}")
    @Column (unique = true, nullable = false)
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Country country;


    @Enumerated(value = EnumType.STRING)
    private Language language;

    @OneToMany
    @JoinColumn
    private Set<Device> devices = new HashSet<>();


    private boolean enambled;



    @ManyToMany(fetch = FetchType.EAGER)
    private Set<com.finalproject.demo.entity.Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
        return enambled;
    }
}

