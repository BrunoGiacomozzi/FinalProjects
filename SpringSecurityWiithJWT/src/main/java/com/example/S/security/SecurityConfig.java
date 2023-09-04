package com.example.S.security;

import com.example.S.security.filter.JwtAuthenticationFilter;
import com.example.S.security.jwt.jwtUtils;
import com.example.S.service.UserDetailsServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    UserDetailsServiceIMPL userDetailsService;

    @Autowired
    jwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https, AuthenticationManager authenticationManager) throws Exception {

       

        return https // comportamineto de acceso a los end point, solo se puede acceder a hello sin autorizaion
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/hello").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                //.httpBasic()// para la autenticacion basica necesitas un usuario en memoria(UserDetailsService()) por eso creamos ese metodo
                // saamos al autenticacion basica de http y ponemos la jwt
                
                .build();

    }

    /*
    // usuario de acceso para tener acceso basico
    @Bean
    public UserDetailsService UserDetailsService(){
        
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); // paraa crear un usuario en memoria
        
        manager.createUser(User.withUsername("user")
        .password("password")
        .roles("ADMIN")
        .build());
        
        return manager;  
    }
    // y el authenticationManager() nos pide un password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        
        return new BCryptPasswordEncoder(); // una vez enriptado no sse puede sdesencriptar 
        
    }
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // autentication --> Administracion de la autorizacion del usuario
    // tambien requiere un password encouder
    // para poder usar el usuario en memoria tenemos que administrar su autenticacion authenticationManager()
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity https, PasswordEncoder passwordEncoder) throws Exception {

        return https
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();

    }

}
