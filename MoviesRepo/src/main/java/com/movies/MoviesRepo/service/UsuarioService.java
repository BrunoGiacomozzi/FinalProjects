
package com.movies.MoviesRepo.service;

import com.movies.MoviesRepo.entity.Peliculas;
import com.movies.MoviesRepo.entity.UsuarioEntidad;
import com.movies.MoviesRepo.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public UsuarioEntidad findbyId( Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    
    public List<UsuarioEntidad> listarUsuarios(){
        
        return usuarioRepository.findAll();
        
    }
    
    public UsuarioEntidad findByName(String name){
        return usuarioRepository.findByName(name);
    }
    
    public UsuarioEntidad agregarPelculaALaLista(Peliculas peliculas,UsuarioEntidad usuario ){
        List<Peliculas> pelisfav = new ArrayList();
        
        pelisfav.add(peliculas);
        
        usuario.setListaPeliculas(pelisfav);
        
        return usuario;
        
    }
    
    public UsuarioEntidad finByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    
    public List<UsuarioEntidad> findByRol(String rol){
        return usuarioRepository.findByRol(rol);
    }
    
    public UsuarioEntidad crearUsuario(UsuarioEntidad usuarioEntidad ){
        
        return usuarioRepository.save(usuarioEntidad);
        
    }
    
    public void borrarUsuario(UsuarioEntidad usuarioEntidad){
        usuarioRepository.delete(usuarioEntidad);
    }
    
     public void borrarUsuarioPorId(Long id){
       usuarioRepository.deleteById(id);
    }
     
     public UsuarioEntidadDTO login(String userName, String password){
         
        UsuarioEntidad usuarioEntidad = usuarioRepository.findByUserAndPassword(userName, password);
        // le pasamos todos los parametros
        UsuarioEntidadDTO usuarioEntidadDTO = 
                new UsuarioEntidadDTO(usuarioEntidad.getId(), usuarioEntidad.getEmail(), usuarioEntidad.getUserName()
                        , usuarioEntidad.getRol(),usuarioEntidad.getListaPeliculas()) ;
        
        return usuarioEntidadDTO;
     }
}
