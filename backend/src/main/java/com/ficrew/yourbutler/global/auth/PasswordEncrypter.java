package com.ficrew.yourbutler.global.auth;

public interface PasswordEncrypter {
    String encrypt(String textPassword);

    boolean match(String rawPassword, String encryptedPassword);

}
