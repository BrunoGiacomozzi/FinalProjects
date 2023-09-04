
package com.api.gestion.Api.gestion.facturas.jwt;



import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

// estclase va a validar los tokens de acceso
@Service
public class JwtUtils {
    
    private final String secretKey = "SpringBoot";
    
    public Date extractExpiration(String token){
        
        return extractClaims(token,Claims::getExpiration);
        
    }
    
    
    public String extractUserName(String token){
        
        return extractClaims(token, Claims::getSubject);
        
    }
    
    
    // generico
    public <T> T  extractClaims(String token, Function<Claims, T> claimsResolver){
        
        final Claims claims = extractAllClaiams(token);
        
        return claimsResolver.apply(claims);
        
    }
    
    public Claims extractAllClaiams(String token ){
        
        return (Claims) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody() ;
        
    }
    
    // verificar si el metodo es valido
    
    private Boolean isTokenExpired(String token){
        
        return extractExpiration(token).before(new Date());
        
    }
    
    // generar el token de acceso
    public String generatedToken(String userName, String role){
        
        Map <String, Object> claims = new HashMap<>();
        claims.put("role", role);
        
        return createdToken(claims, userName);
        
    }
    
    private String createdToken( Map <String, Object> claims, String subject){
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
        
    }
    
    private Boolean validateToken(String token, UserDetails userDetails){
        
        final String userName = extractUserName(token);
        
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token) );
        
    }
    
    /*
    Otra forma de hacer con io.jsonwebtoken
    
    public class JwtUtil {

    private static final String secretKey = "secretKey";

    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 24);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}

    */
}
