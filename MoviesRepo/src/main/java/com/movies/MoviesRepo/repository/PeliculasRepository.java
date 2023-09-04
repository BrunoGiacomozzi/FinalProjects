
package com.movies.MoviesRepo.repository;

import com.movies.MoviesRepo.entity.Peliculas;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface PeliculasRepository extends JpaRepository<Peliculas, Long>{
    
    @Query("SELECT p FROM peliculas p WHERE p.nombre = :nombre")
    public Peliculas findByName(@Valid @RequestParam(name = "nombre") String nombre);
}
