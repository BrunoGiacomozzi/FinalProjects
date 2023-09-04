
package com.movies.MoviesRepo.security.jwtfilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.MoviesRepo.entity.UsuarioEntidad;
import com.movies.MoviesRepo.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private JwtUtils jwtUtils;
    
    
    public JwtAuthenticationFilter(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
            throws AuthenticationException {
        
        UsuarioEntidad userEntidad = null;
        String userName = "";
        String password = "";
        
        try{
            // viene en formato json tendriamos que hacer un castearr a objeto java
            // toma los parametros de userentity password, username, email o rol y los mapeaa a un userentity
            userEntidad = new ObjectMapper().readValue(request.getInputStream(), UsuarioEntidad.class);
            
            userName = userEntidad.getUserName();
            password = userEntidad.getPassowrd();
            
        }catch(AuthenticationException e){
            throw new RuntimeException(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(JwtAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        // si no hay ningun error al mapear el los datos de request al userEntity, nos autentica en la aplicacion
        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(userName, password);
        
        return getAuthenticationManager().authenticate(authenticationToken); 
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response
            , FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // vamos a obtener lso detalles del usuario que seaha autenticado
        User user = (User) authResult.getPrincipal(); // obtenemos el objeto que contiene todos los parametros del usuario
        // generar el token de acceso para dar autorizacion a los end points
        String token = jwtUtils.generatedToken(user.getUsername());
        
        // debemos responder co nel token de accesos
        response.addHeader("Autorization", token);
        
        // convertir la respuesta en  un json
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message: ","Autorization succesfull");
        httpResponse.put("user Name: ", user.getUsername());
            
        // escribir el mapa con un json en la respesta
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        // podemos agregar mas parametros a la respuesta
        response.setStatus(HttpStatus.OK.value()); // retorna un 200, que es lo mismo que: response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);// que es lo mismo que response.setContentType("application/json")
        response.getWriter().flush();
        
        super.successfulAuthentication(request, response, chain, authResult); 
    }
}
