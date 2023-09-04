package com.api.gestion.Api.gestion.facturas.service.impl;

import com.api.gestion.Api.gestion.facturas.constantes.FacturasConstantes;
import com.api.gestion.Api.gestion.facturas.dao.UserDAO;
import com.api.gestion.Api.gestion.facturas.pojo.UserEntity;
import com.api.gestion.Api.gestion.facturas.service.UserEntityService;
import com.api.gestion.Api.gestion.facturas.util.FacturaUtils;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j// se utiliza para declarar que una clase utilizará el API de registro de SLF4J. SLF4J es un marco de registro de nivel superior 
//que proporciona una interfaz común para diferentes implementaciones de registro, como Logback y Log4j.
public class UserEntityServiceImpl implements UserEntityService {
    
    @Autowired
    private UserDAO userDAO;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> reqestMap) {
        log.info("Registro interno de un usuario {}", reqestMap);

        try {
            // validamos que los datoa del mapa no esten vacios
            if (validateSignUp(reqestMap)) {
                // si valida los datos lo guardamos
                UserEntity userntity = userDAO.findByEmail(reqestMap.get("email"));
                // si es nulo se guardara
                if(Objects.isNull(userntity)){
                    
                    userDAO.save(userntity);
                    
                    return FacturaUtils.getResponseEntity("Usuario registrado con exito!-", HttpStatus.CREATED);
                }
                else{
                    return  FacturaUtils.getResponseEntity("El usuario con nese email ya existe!.", HttpStatus.BAD_REQUEST);
                }
                
            }
            
            else{
                
                return FacturaUtils.getResponseEntity(FacturasConstantes.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturasConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // metodo para validar los datos
    private boolean validateSignUp(Map<String, String> reqestMap) {
        if (reqestMap.containsKey("nombre") && reqestMap.containsValue("contactNumber")
                && reqestMap.containsValue("email") && reqestMap.containsValue("password")) {

            return true;
        }
        return false;
    }

    // para guardar los datos del mapa tenemos que crear este metodo
    private UserEntity getUserFromMap(Map<String, String> reqestMap) {

        UserEntity userEntity = new UserEntity();

        userEntity.setName(reqestMap.get("name"));
        userEntity.setEmail(reqestMap.get("email"));
        userEntity.setContactNumber(reqestMap.get("contactNumber"));
        userEntity.setPassword(reqestMap.get("password"));
        userEntity.setRol("user");// para asignar el rol de admin va a ser un metodo especial
        userEntity.setStatus("false");

        return userEntity;

    }

}
