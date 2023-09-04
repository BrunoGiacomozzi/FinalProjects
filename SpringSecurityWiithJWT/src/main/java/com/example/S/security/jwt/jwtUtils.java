package com.example.S.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import static org.hibernate.query.sqm.tree.SqmNode.log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// clase de utileria que nos va a proveer de los metodos necesrios
@Component
public class jwtUtils {

    @Value("${jwt.secret.key}")// encriptaion de la clave secreta 256 bits hexa 
    private String secretKey; // nos va a ayudar a firmar el jwt el permiso

    @Value("${jwt.time.expiratin}")// hacen referencia a propiedades del properties 1 dia de epiration de pass 
    private String timeExpiration;

    /*
    
Un JWT es un token web JSON que se utiliza para transmitir información de seguridad entre un cliente y un servidor. 
Los JWT son seguros porque están firmados digitalmente, lo que dificulta su falsificación.
Consta de 3 partes:
El encabezado: contiene información sobre el JWT, como el algoritmo de firma y el tipo de token.
La carga útil: contiene la información del usuario, como el nombre de usuario, el rol y la fecha de expiración.
La firma digital: se utiliza para verificar la autenticidad del JWT.
El proceso de autenticación con JWT es el siguiente:

El usuario envía sus credenciales al servidor.
El servidor autentica al usuario y genera un JWT.
El servidor envía el JWT al cliente.
El cliente almacena el JWT en el navegador.
El cliente envía el JWT en cada solicitud HTTP al servidor.
    
     */
    // crear un token, generar un token de acceso
    public String generatedAccessToken(String userName) {

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))// fecha de creacio del token
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256) // lo decodificamos y lo encriptamos de vuelta
                .compact();

    }

    // validar el token de acceso
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()// lee el token
                    .setSigningKey(getSignatureKey())
                    .build().parseClaimsJws(token)
                    .getBody();

            return true;

        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
            log.error("Token  invalido, error ".concat(e.getMessage()));
            return false;
        }
    }
    
    /*
    Un claim en JWT es una declaración sobre un sujeto, como el nombre de usuario, el rol o la fecha de expiración. 
    Los claims se almacenan en el cuerpo del token JWT.
    Los claims pueden ser públicos o privados. Los claims públicos son aquellos que están disponibles para cualquier 
    persona que tenga el token JWT. Los claims privados son aquellos que solo son accesibles para el emisor y el receptor 
    del token JWT
    Aquí hay algunos ejemplos de claims comunes:

    sub: El identificador del sujeto
    iss: El emisor del token JWT
    aud: El receptor del token JWT
    exp: La fecha de expiración del token JWT
    nbf: La fecha antes de la cual el token JWT no es válido
    iat: La fecha en la que se emitió el token JWT
    jti: El identificador único del token JWT
    
    Como obtener todos los claims del token:
    */
    
    public Claims extractAllClaims(String token){
        
        return Jwts.parserBuilder()// lee el token
                    .setSigningKey(getSignatureKey())
                    .build().parseClaimsJws(token)
                    .getBody();
        // obtenemos un listado con todos los claims
        
    }
    
    // otener un solo claim y lo podemos hacer generico. recibomo un token, funtion va a recibir los claims 
    //y vamos devolver un T ya que los claims tienen diferentes valores entre ellos
    public <T> T getClaim(String token, Function<Claims, T> claimsFinction){
        // obteemos todos lo claims
        Claims claims = extractAllClaims(token);
        
        return claimsFinction.apply(claims);
                
        
    }
    
    //obtner el usuario del token
    public String getUserFromToken(String token){
        return getClaim(token, claims -> claims.getSubject()); // otra forma: Claims::getSubject();
    }

    // obtener firma del token
    public Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);// deodificar esta clave pero despues la vamos a encriptar de nuevo

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
