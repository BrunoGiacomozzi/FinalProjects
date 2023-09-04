
package com.api.gestion.Api.gestion.facturas.util;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class FacturaUtils {
    
    private  FacturaUtils(){
        
    }
    
    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpstatus){
        return new ResponseEntity<>("Mensaje : " + message,httpstatus);
    }
}
