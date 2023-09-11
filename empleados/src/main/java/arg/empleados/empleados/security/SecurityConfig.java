package arg.empleados.empleados.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        
        return http
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers("/empleados/crear").permitAll().anyRequest().authenticated();
                })
                .csrf(crsf -> crsf.disable())
                .cors(cors -> cors.disable())
                // podriamos agregar un filtro si  quisieramoss
                .build();
        
    }
    
    public PasswordEncoder passwordEncouder(){
        return new BCryptPasswordEncoder();
    }
    
    // crear 
    
}
