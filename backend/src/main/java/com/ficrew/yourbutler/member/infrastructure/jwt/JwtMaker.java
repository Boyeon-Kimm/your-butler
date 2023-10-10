package com.ficrew.yourbutler.member.infrastructure.jwt;

import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import com.ficrew.yourbutler.global.auth.JWTProvider;
import com.ficrew.yourbutler.global.auth.Token;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import io.jsonwebtoken.security.SignatureException;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class JwtMaker implements JWTProvider {
    @Value("${jwt.secret_key}")
    private String jwtSecret;
    @Value("${jwt.access_expiration_ms}")
    private long accessExpirationMs;

    @Value("${jwt.refresh_expiration_ms}")
    private long refreshExpirationMs;
    @Value("${jwt.issuer}")
    private String issuer;

    private final AuthenticationManager authenticationManager;

    @Override
    public Token generateTokens(String userId, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userId, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return makeToken(authentication);
    }

    @Override
    public Token reissueAccessToken(String userId, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userId, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return makeAccessToken(authentication);
    }

    @Override
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    private Token makeToken(Authentication authentication) {
        AuthenticatedMember userPrincipal = (AuthenticatedMember) authentication.getPrincipal();

        String accessToken = Jwts.builder()
                                .setSubject(userPrincipal.getUsername())
                                .setIssuer(issuer)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date((new Date()).getTime() + accessExpirationMs))
                                .claim("id", userPrincipal.getId())
                                .claim("role", userPrincipal.getAuthority())
                                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                                .compact();

        String refreshToken = Jwts.builder()
                                .setSubject(userPrincipal.getUsername())
                                .setIssuer(issuer)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date((new Date()).getTime() + refreshExpirationMs))
                                .claim("id", userPrincipal.getId())
                                .claim("role", userPrincipal.getAuthority())
                                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                                .compact();

        return new Token(accessToken, refreshToken);
    }

    private Token makeAccessToken(Authentication authentication) {
        AuthenticatedMember userPrincipal = (AuthenticatedMember) authentication.getPrincipal();

        String accessToken = Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + accessExpirationMs))
                .claim("id", userPrincipal.getId())
                .claim("role", userPrincipal.getAuthority())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return new Token(accessToken);
    }
}
