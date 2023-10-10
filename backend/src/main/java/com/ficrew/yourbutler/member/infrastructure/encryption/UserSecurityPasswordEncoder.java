package com.ficrew.yourbutler.member.infrastructure.encryption;

import com.ficrew.yourbutler.global.auth.PasswordEncrypter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserSecurityPasswordEncoder implements PasswordEncrypter {

    private final PasswordEncoder encoder;
    @Override
    public String encrypt(String textPassword) {
        return encoder.encode(textPassword);
    }

    @Override
    public boolean match(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }
}
