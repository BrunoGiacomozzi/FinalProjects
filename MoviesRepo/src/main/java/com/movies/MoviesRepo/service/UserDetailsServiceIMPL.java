
package com.movies.MoviesRepo.service;

import com.movies.MoviesRepo.entity.ERol;
import com.movies.MoviesRepo.entity.UsuarioEntidad;
import com.movies.MoviesRepo.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailsServiceIMPL implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // recuperamos el usuariode la base de daros
        UsuarioEntidad userEntidad = usuarioRepository.findByName(username);
        List<ERol> roles = new ArrayList();
        
       Collection<? extends GrantedAuthority> authorities;
        authorities = (Collection<? extends GrantedAuthority>)(Object)roles.add( userEntidad.getRol());
        
        return new User(username, userEntidad.getPassowrd(), true, true, true, true, authorities);
        
    }
    
    
}
