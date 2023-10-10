package com.ficrew.yourbutler.global.auth;

import com.ficrew.yourbutler.member.domain.entity.Member;
import com.ficrew.yourbutler.member.domain.entity.NumberOfHouses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Getter
@AllArgsConstructor
public class AuthenticatedMember implements UserDetails {

    private Long id;
    private String identifier;
    private String socialType;
    private String email;
    private String password;
    private String authority;
    private Integer creditRating;
    private NumberOfHouses numberOfHouses;

    public static AuthenticatedMember entityToDto(Member member) {
        return new AuthenticatedMember(
            member.getId(),
            member.getIdentifier(),
            member.getSocialType(),
            member.getEmail(),
            member.getPassword(),
            member.getRole().name(),
            member.getCreditRating(),
            member.getNumberOfHouses()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return socialType + " " + identifier;
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
