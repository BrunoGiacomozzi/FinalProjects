
package com.example.S.controller;

import com.example.S.controller.request.CreateUserDTO;
import com.example.S.models.ERole;
import com.example.S.models.RoleEntity;
import com.example.S.models.UserEntity;
import com.example.S.repository.UserRepository;
import jakarta.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PrincipalController {
    // tenemos que encriptar el password antes de envairlo a la base de datos
    
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/hello")
    public String hello(){
        
        return "Hello World Not Secured";
        
    }
    
    @GetMapping("/hellosecured")
    public String helloSecured(){
        
        return "Hello World  Secured";   
    }
    
   @PostMapping("/createuser")// RequestBody esta nfo va a venir en el body del request , @Valid para qe qse valider 
    public ResponseEntity<?> createUser( @Valid @RequestBody CreateUserDTO createUserDTO){ // automaticamnete que esos caposse sean correctos
        // recibimo el usuario por medio de la peticion http, tenemos que crear un CreateUserDTO
        
        // tenemos que pasar el set<STring< a Set<RoleEntity>
        Set<RoleEntity> roles = (Set<RoleEntity>) createUserDTO.getRoles().stream()
                .map(rol -> RoleEntity.builder()
                .rolName(ERole.valueOf(rol))
                .build())
                .collect(Collectors.toSet());
        /*Otra forma de hacer el el set<STring< a Set<RoleEntity>
        En lugar de utilizar el método map() para crear un conjunto de objetos RoleEntity, podrías utilizar el método 
        stream() y el método collect(). Esto te permitiría crear el conjunto de objetos RoleEntity de forma más eficiente.
        
        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(ERole::valueOf)
                .collect(Collectors.toSet());
        */
        // aca la importancia del @builder
         UserEntity userEntity = UserEntity.builder()
                 .userName(createUserDTO.getUserName())
                 // encriptamos el password para que se envie asi a la base de datos
                 .password(passwordEncoder.encode(createUserDTO.getPassword()))
                 .email(createUserDTO.getEmail())
                 .roles(roles)
                 .build();
         
         /*Otra forma de traer los datos del usuario
          En lugar de utilizar el método builder() para crear un objeto UserEntity, podrías utilizar el método of(). 
        Esto te permitiría crear el objeto UserEntity de forma más concisa.
         
         UserEntity userEntity = UserEntity.of(
                createUserDTO.getUserName(),
                createUserDTO.getPassword(),
                createUserDTO.getEmail(),
                roles);
         */
         
         // esto hay que enviarlo a la base de datos guardarlo
         userRepository.save(userEntity);
        
         // tenemos que responder con el userEentity
        return (ResponseEntity<?>) ResponseEntity.ok(userEntity);
        
    }
    
    // Para que solo un administrador pueda borrar un usuario, puedes utilizar la anotación @PreAuthorize para especificar que el 
    //método solo puede ser ejecutado por usuarios con el rol ADMIN
    
    @DeleteMapping("/deleteuser") // @DeleteMapping especifica que el método debe ser invocado por una solicitud HTTP DELETE. 
    //@PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@RequestParam String id){ // La anotación @RequestParam especifica que el parámetro id debe ser inyectado en el método como un parámetro de consulta.
        userRepository.deleteById(Long.parseLong(id));
        
        return "Se ha borrado un el user con id:".concat(id);
        
    }
            
}
