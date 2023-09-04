
package com.movies.MoviesRepo.repository;

import com.movies.MoviesRepo.entity.UsuarioEntidad;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntidad, Long> {
    
    @Query("SELECT e FROM entidadusuario e WHERE e.nombre = :nombre")
    public UsuarioEntidad findByName(@Valid @RequestParam("nombre") String nombre);
    
    @Query("SELECT e FROM entidadusuario e WHERE e.email = :email")
    public UsuarioEntidad findByEmail(@Valid @RequestParam("email") String email);
    
    @Query("SELECT e FROM entidadusuario e WHERE e.rol = :rol")
    public List<UsuarioEntidad> findByRol(@Valid @RequestParam("rol") String rol);
    
    @Query("SELECT e FROM entidadusuario e WHERE e.username = :username AND e.password= :password")
    public UsuarioEntidad findByUserAndPassword(@RequestParam("username")String userName, 
            @RequestParam("password") String passwrod);
}
