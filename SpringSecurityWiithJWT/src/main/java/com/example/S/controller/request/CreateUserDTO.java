
package com.example.S.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // crea un constructor co todos los parametros
@NoArgsConstructor// crea un constructo sin argumentos
public class CreateUserDTO {
    
    //va a tener los mismo atribuos que UuserEntity
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    
    // con String para poder enviarle los roles desde nuestra peticion
    private Set<String> roles;
    
    // creada el DTO podemos ir al controller createUser
    
}
