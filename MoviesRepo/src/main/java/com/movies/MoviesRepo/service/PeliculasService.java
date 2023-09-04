
package com.movies.MoviesRepo.service;

import com.movies.MoviesRepo.entity.Peliculas;
import com.movies.MoviesRepo.repository.PeliculasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculasService {
    
    @Autowired
    private PeliculasRepository peliculasRepository;
    
    public Peliculas crearPeliculas(Peliculas pelicula){
        return peliculasRepository.save(pelicula);
    }
    
    public Peliculas findById(Long id){
        return peliculasRepository.findById(id).orElse(null);
    }
    
    public List<Peliculas> listarPeliculas(){
        return peliculasRepository.findAll();
    }
    
    public Peliculas findByName(String nombre){
        return peliculasRepository.findByName(nombre);
    }
    
    public void borrarPeliPorId(Long id){
       peliculasRepository.deleteById(id);
    }
    
}
