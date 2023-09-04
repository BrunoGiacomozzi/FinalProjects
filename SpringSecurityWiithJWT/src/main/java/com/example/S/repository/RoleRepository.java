
package com.example.S.repository;

import com.example.S.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends CrudRepository<RoleEntity, Long>{
    
    
    
}
