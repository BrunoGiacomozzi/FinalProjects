
package com.api.gestion.Api.gestion.facturas.rest;

import com.api.gestion.Api.gestion.facturas.constantes.FacturasConstantes;
import com.api.gestion.Api.gestion.facturas.service.UserEntityService;
import com.api.gestion.Api.gestion.facturas.util.FacturaUtils;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user")
public class UserController {
    
    @Autowired
    private UserEntityService userService;
    
    // crea un usuario en la bas de datos
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody(required = true) Map<String, String> requestMap){
        try{
            return  userService.signUp(requestMap);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturasConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
    
}
