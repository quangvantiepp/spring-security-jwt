package nature.sales_website.jwt;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import nature.sales_website.data.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {

    private  final String JWT_SECRET ="quang_van_tiep";
    // 604800000L ms = 7 days,
//    private final  long JWT_EXPIRATION = 604800000L;
    // 30 minutes
    private final  long JWT_EXPIRATION = 1800L;

    public String generateToken(CustomUserDetails customUserDetails){
        Date dateNow = new Date();
        Date expriryDate = new Date(dateNow.getTime() + JWT_EXPIRATION);

        // add more info
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", customUserDetails.getAuthorities().stream().map((GrantedAuthority::getAuthority)).collect(Collectors.toList()));
        claims.put("fullName", customUserDetails.getUser().getFullName());
        claims.put("email", customUserDetails.getUser().getEmail());
        claims.put("userName", customUserDetails.getUser().getUserName());

        // Create jwt from id of the user
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Long.toString(customUserDetails.getUser().getId()))
                .setIssuedAt(dateNow)
                .setExpiration(expriryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
    public  Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public  boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
