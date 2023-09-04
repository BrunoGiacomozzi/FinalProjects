
package com.movies.MoviesRepo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name="username")
    private String userName;
    
    @NotBlank
    @Email
    @Column(name="email")
    private String email;
    
    @NotBlank
    @Column(name="password")
    private String passowrd;
    
    @Column(name="listapeliculas")
    @ManyToOne
    private List<Peliculas> listaPeliculas;
    
    @Column(name="rol")
    @OneToOne
    private ERol rol;
}
