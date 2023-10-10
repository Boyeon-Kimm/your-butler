package com.ficrew.yourbutler.global.auth;

import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService{
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] identifier = username.split(" ");
        return AuthenticatedMember.entityToDto(memberRepository.findBySocialTypeAndIdentifier(identifier[0], identifier[1]));
    }
}
