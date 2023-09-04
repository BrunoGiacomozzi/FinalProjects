package com.api.gestion.Api.gestion.facturas.dao;

import com.api.gestion.Api.gestion.facturas.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Integer> {

    
    UserEntity findByEmail(@Param(("email")) String email);
}
