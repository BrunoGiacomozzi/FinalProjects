
package com.movies.MoviesRepo.service;

// el dto se usa para no traer info innecesaria o sensible de la base de datos al fronted

import com.movies.MoviesRepo.entity.ERol;
import com.movies.MoviesRepo.entity.Peliculas;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntidadDTO {
    
    private Long id;
    private String email;
    private String userName;
    private ERol rol;
    private List<Peliculas> peliculas;
    
}
