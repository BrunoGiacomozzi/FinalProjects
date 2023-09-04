
package com.example.S.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // crea un constructor co todos los parametros
@NoArgsConstructor// crea un constructo sin argumentos
@Builder// implementa el patron de creacion builder en las entidades
@Entity
@Table(name="roles")
public class RoleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)// como en esa clase tenemos cadenas de texto usamos STRING
    private ERole rolName;
}
