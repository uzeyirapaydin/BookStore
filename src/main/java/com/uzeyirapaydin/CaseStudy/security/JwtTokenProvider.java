package com.uzeyirapaydin.CaseStudy.security;

import com.uzeyirapaydin.CaseStudy.config.ApplicationProperties;
import com.uzeyirapaydin.CaseStudy.exception.AuthenticationAPIException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final ApplicationProperties applicationProperties;

    public String generateJwtToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + (applicationProperties.getJwtExpiration() * 3600000)))
                .signWith(SignatureAlgorithm.HS512, applicationProperties.getJwtSecret())
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(applicationProperties.getJwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            throw new AuthenticationAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new AuthenticationAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new AuthenticationAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new AuthenticationAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new AuthenticationAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(applicationProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

}
