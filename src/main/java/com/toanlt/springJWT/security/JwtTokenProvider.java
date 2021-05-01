package com.toanlt.springJWT.security;

import com.toanlt.springJWT.user.model.AppUser;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.keysecret}")
    private String JWT_SECRET;

    private static final long JWT_EXPIRATION = 604800000L;

    public String generateToken(AppUser appUser) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder().setSubject(appUser.getUsername()).setIssuedAt(now)
                .setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    }

    public String getUserFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            throw new IllegalStateException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new IllegalStateException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new IllegalStateException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("JWT claims string is empty.");
        }
    }
}
