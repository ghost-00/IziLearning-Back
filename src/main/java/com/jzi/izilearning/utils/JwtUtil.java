package com.jzi.izilearning.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtUtil {
  private final String jwtSecret = generateSecretKeyBase64();

  // Generate a JWT token for a user
  public String generateJwtToken(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + 86400000))
            .signWith(getSigningKey())
            .compact();
  }

  private String generateSecretKeyBase64(){
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Generate 512-bit key
    return Base64.getEncoder().encodeToString(key.getEncoded());
  }
  // Get username from the JWT token
  public String getUserNameFromJwtToken(String token) {
    return getClaims(token).getSubject();
  }

  // Extract All Claims
  public Claims getClaims(String token) {
    try {
      return Jwts.parserBuilder()
              .setSigningKey(getSigningKey())
              .build()
              .parseClaimsJws(token)
              .getBody();
    } catch (ExpiredJwtException e) {
      throw new RuntimeException("Token expired!");
    } catch (JwtException e) {
      throw new RuntimeException("Invalid token!");
    }
  }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  public boolean validateJwtToken(String token, String userName) {
    try {
      // Parse the token and check its signature and expiration date
      Claims claims = Jwts.parserBuilder()
              .setSigningKey(getSigningKey())
              .build()
              .parseClaimsJws(token)
              .getBody();

      // Check if the username matches the token's subject and if it's not expired
      return (userName.equals(claims.getSubject()) && !isTokenExpired(claims));
    } catch (SignatureException e) {
      System.out.println("Invalid JWT signature");
    } catch (MalformedJwtException e) {
      System.out.println("Invalid JWT token");
    } catch (ExpiredJwtException e) {
      System.out.println("JWT token is expired");
    } catch (UnsupportedJwtException e) {
      System.out.println("JWT token is unsupported");
    } catch (IllegalArgumentException e) {
      System.out.println("JWT claims string is empty");
    }
    return false;
  }

  // Method to check if the token has expired
  private boolean isTokenExpired(Claims claims) {
    return claims.getExpiration().before(new Date());
  }
}
