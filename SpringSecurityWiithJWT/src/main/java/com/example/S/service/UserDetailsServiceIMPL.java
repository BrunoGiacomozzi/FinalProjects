package com.example.S.service;

import com.example.S.models.UserEntity;
import com.example.S.repository.UserRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import static org.hibernate.Hibernate.collection;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.core.convert.TypeDescriptor.collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceIMPL implements UserDetailsService {

    // para que  consulte a la base de datos
    @Autowired
    private UserRepository userRepository;

    // vamso a decirle de donde va  buscar los datos que va a autenticar del usuario en la base de datos
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // recuperariamo el usuario de la base de datos
        UserEntity userEntity = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe " + username));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority("ROLE ".concat(role.getRolName().name())
        )).collect(Collectors.toSet());

        return new User(userEntity.getUserName(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

}
