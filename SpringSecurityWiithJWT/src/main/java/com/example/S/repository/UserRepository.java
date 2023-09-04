
package com.example.S.repository;

import com.example.S.models.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    
    
    Optional<UserEntity> findByUserName(String userName);
    
    // so dos formas de buscar lo mismo pero jpa con el nombre del metodo sabe donde buscar(findByUserName)cuando lo cambias no sabe 
    //donde buscar entonces usamos la query para seleccionar donde debe buscar
   // @Query("SELECT u FROM userentity u WHERE u.username = ?1")
    //Optional<UserEntity> getName(String userName);
    
}
