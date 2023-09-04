
package com.movies.MoviesRepo.controller;

import com.movies.MoviesRepo.entity.Peliculas;
import com.movies.MoviesRepo.entity.UsuarioEntidad;
import com.movies.MoviesRepo.service.PeliculasService;
import com.movies.MoviesRepo.service.UsuarioEntidadDTO;
import com.movies.MoviesRepo.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {
    @Autowired
    private PeliculasService peliculasService;
    @Autowired
    private UsuarioService usuarioService ;
    
    @GetMapping("/peliculas")
    @ResponseBody
    public List<Peliculas> listarPelis(){
        return peliculasService.listarPeliculas();
    }
    
    @PostMapping("/peliculas")
    @ResponseBody
    public Peliculas crearPeliculas(@RequestBody @Valid Peliculas pelicula){
        return peliculasService.crearPeliculas(pelicula);
    }
    
    @DeleteMapping("/peliculas/{id}")
    public void borrarPeli(@PathVariable Long id){
       peliculasService.borrarPeliPorId(id);
    }
    
    @GetMapping("/Peliculas/{id}")
    @ResponseBody
    public Peliculas buscarPorId(@PathVariable Long id){
        return peliculasService.findById(id);
    }
    
    @GetMapping("/Peliculas/{nombre}")
    @ResponseBody
    public Peliculas buscarPorNombre(@PathVariable String nombre){
        return peliculasService.findByName(nombre);
    }
    
    
    
    
     @GetMapping("/usurios")
    @ResponseBody
    public List<UsuarioEntidad> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }
    
    @PostMapping("/usurios")
    @ResponseBody
    public UsuarioEntidad crearPeliculas(@RequestBody @Validated UsuarioEntidad usuarioEntidad){
        return usuarioService.crearUsuario(usuarioEntidad);
    }
    
    
    @PostMapping("/usuarios/{id}")
    @ResponseBody
    public void borrarUsuario(@RequestBody @Validated Long id){
        usuarioService.borrarUsuarioPorId(id);
    }
    
    @GetMapping("/usuarios/{nombre}")
    @ResponseBody
    public UsuarioEntidad buscarUsuarioPorNombre(@PathVariable String nombre){
        return usuarioService.findByName(nombre);
    }
    
   @GetMapping("/usuarios/{id}")
    @ResponseBody
    public UsuarioEntidad buscarUsuarioPorId(@PathVariable Long id){
        return usuarioService.findbyId(id);
    }
    
    @GetMapping("/usuarios/{email}")
    @ResponseBody
    public UsuarioEntidad buscarUsuarioPorEmail(@PathVariable String email){
        return usuarioService.finByEmail(email);
    }
    
   // LOGN  
    
    @PostMapping("/login")
    public UsuarioEntidadDTO login(@RequestBody @Valid UsuarioEntidad usuarioEntidad){
        
        return usuarioService.login(usuarioEntidad.getUserName(), usuarioEntidad.getPassowrd());
        
    }   
}
