package com.example.S;

import com.example.S.models.ERole;
import com.example.S.models.RoleEntity;
import com.example.S.models.UserEntity;
import com.example.S.repository.UserRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
        System.out.println("Funcionando.....");
        // al borrarse la tablas a al levanatr el server tenemos que ecrear un usuario 
        //apenas se levantarse  el server        
    }
    

    /*
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Bean
    CommandLineRunner init() {

        return args -> {
            UserEntity userEntity;
            userEntity = UserEntity.builder()
                    .email("brunogiacomozzi@gmail.com")
                    .userName("bruno")
                    .password(passwordEncoder.encode("popis"))
                    .roles(Set.of(RoleEntity.builder()
                            .rolName(ERole.valueOf(ERole.ADMIN.name())).build()))
                    .build();

            userRepository.save(userEntity);

            UserEntity userEntity2;
            userEntity2 = UserEntity.builder()
                    .email("pedro@gmail.com")
                    .userName("pedro")
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(RoleEntity.builder()
                            .rolName(ERole.valueOf(ERole.USER.name())).build()))
                    .build();

            userRepository.save(userEntity2);

            UserEntity userEntity3;
            userEntity3 = UserEntity.builder()
                    .email("andrea@gmail.com")
                    .userName("andrea")
                    .password(passwordEncoder.encode("jorge"))
                    .roles(Set.of(RoleEntity.builder()
                            .rolName(ERole.valueOf(ERole.INVITED.name())).build()))
                    .build();

            userRepository.save(userEntity3);
        };
    }
*/

}
