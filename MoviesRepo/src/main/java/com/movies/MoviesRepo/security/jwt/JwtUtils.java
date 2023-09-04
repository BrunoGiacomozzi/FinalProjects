
package com.movies.MoviesRepo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import static org.hibernate.query.sqm.tree.SqmNode.log;

@Component
public class JwtUtils {
    
    private static final String secretKey="secretKey";
    
    public String generatedToken(String userName){
        Date now = new Date();
        Map<String, Object> claims = new HashMap<>();
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 1000 *60 *60 *23))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public boolean isTokenValid(String token){
        try{
             Jwts.parserBuilder().setSigningKey(getSignatureKey())
                     .build().parseClaimsJws(token).getBody();
            
            return true;
            
        }catch(JwtException e){
            log.error("Token invaido! " + e.getMessage());
            return false;
        }
    }
    
     public Claims extractAllClaims(String token){
        
        return Jwts.parserBuilder()// lee el token
                    .setSigningKey(getSignatureKey())
                    .build().parseClaimsJws(token)
                    .getBody();
        // obtenemos un listado con todos los claims
        
    }
     public <T> T getClaim(String token, Function<Claims, T> claimsFinction){
        // obteemos todos lo claims
        Claims claims = extractAllClaims(token);
        
        return claimsFinction.apply(claims);
                
        
    }
    
    //obtner el usuario del token
    public String getUserFromToken(String token){
        return getClaim(token, claims -> claims.getSubject()); // otra forma: Claims::getSubject();
    }
    
    public Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);// deodificar esta clave pero despues la vamos a encriptar de nuevo

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
