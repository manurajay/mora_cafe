package com.mora.cafe.com.mora.cafe.security.jwt;

import java.security.Key;
import java.util.Date;

import com.mora.cafe.com.mora.cafe.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${security.jwt.secret-key}")
  private String jwtSecret;

  @Value("${security.jwt.expiration-time}")
  private String jwtExpirationMs = "3600000"; // Default 1 hour in milliseconds

  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    // Convert the string to long
    long expirationTime;
    try {
      expirationTime = Long.parseLong(jwtExpirationMs);
    } catch (NumberFormatException e) {
      // Handle potential exception and set a default value
      expirationTime = 3600000; // Default to 1 hour
    }

    return Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + expirationTime))
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();
  }

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      System.out.println("ambo1");
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      System.out.println("ambo2");
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      System.out.println("ambo3");
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println("ambo4");
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
