package com.connectionlink.backend.iam.infrastructure.authorization.sfs.model;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String fullName;
    private final String description;
    private final String profileImageUrl;
    private final String bannerImageUrl;
    private final String email;
    private final Integer age;
    private final Date birthday;
    private final Boolean isSpecialistUser;
    private final String cvUrl;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String fullName, String description, String profileImageUrl, String bannerImageUrl, String email, Integer age, Date birthday, Boolean isSpecialistUser, String cvUrl, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.fullName = fullName;
        this.description = description;
        this.profileImageUrl = profileImageUrl;
        this.bannerImageUrl = bannerImageUrl;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
        this.isSpecialistUser = isSpecialistUser;
        this.cvUrl = cvUrl;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        var authorities = user.getRoles().stream().map(role -> role.getName().name()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UserDetailsImpl(user.getUsername(), user.getFullName(), user.getDescription(), user.getProfileImageUrl(), user.getBannerImageUrl(), user.getEmail(), user.getAge(), user.getBirthday(), user.getIsSpecialistUser(), user.getCvUrl(), user.getPassword(),authorities);
    }
}
