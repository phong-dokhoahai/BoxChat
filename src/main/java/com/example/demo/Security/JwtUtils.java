package com.example.demo.Security;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Getter
public class JwtUtils {


    @Value("${jwtSecret}")
    private  String jwtSecret;
    @Value("${jwtAccessExpirationMs}")
    private  int jwtAccessExpirationMs;// 1day
    @Value("${jwtRefreshExpirationMs}")
    private  int jwtRefreshExpirationMs;// 10day

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            String username =Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody().getSubject();
            System.out.println("username(JwtUtils_validateJwtToken):"+username);
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

    public Map<String, String> generateTokenFromUsername(String username) {
        Map<String, String> tokens = new HashMap<>();

        JwtBuilder accessTokenBuilder = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + jwtAccessExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret);
        //System.out.println("jwtAccessExpirationMs :"+jwtAccessExpirationMs);
        String accessToken = accessTokenBuilder.compact();
        tokens.put("accessToken", accessToken);

        // accessToken vs refreshToken can be difference
        JwtBuilder refreshTokenBuilder = Jwts.builder()
                .setSubject(username+"456")
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret);
        String refreshToken = refreshTokenBuilder.compact();
        tokens.put("refreshToken", refreshToken);

       // System.out.println(tokens);
        return tokens;
    }
    public String createAccessTokenFromRefreshToken(String refreshToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(refreshToken)
                .getBody();

        String username = claims.getSubject();

        JwtBuilder accessTokenBuilder = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + jwtAccessExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret);

        return accessTokenBuilder.compact();
    }
}
