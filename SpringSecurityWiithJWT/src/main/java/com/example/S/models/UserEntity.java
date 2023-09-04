
package com.example.S.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // crea un constructor co todos los parametros
@NoArgsConstructor// crea un constructo sin argumentos
@Builder// implementa el patron de creacion builder en las entidades
@Entity
@Table(name="users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// para que jpa genere automaticamente el id
    private Long id;
    
    @Email
    @NotBlank
    @Size(max = 80)
    private String email;
    
    @NotBlank
    @Size(max = 30)
    private String userName;
    
    @NotBlank
    private String password;
    
    // el set para que no se repitan los roles(No se duplican los elementos)
   //FetchType.EAGER para que me traiga todos los roles de ese usuario de una vez
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    // llave foranea, tabla intermedia entre user y roles que se llama user roles, user_id clave foranea de user y rol_id clave foranea de rol
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="rol_id") )
    private Set<RoleEntity> roles;// CascadeType.PERSIST cunado ingrese un usuario a la base de datosnecesito qeu de una ve inserte los roles 
    // pero si el usuario se elimina no puedo permitir que borre los roles y necesito que me ahorre los roles para otros usuarios
    // FetchType.EAGER con cual entidad se va a establecer una relacion
}
