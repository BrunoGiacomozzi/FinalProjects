
package com.api.gestion.Api.gestion.facturas.service;

import java.util.Map;
import org.springframework.http.ResponseEntity;


public interface UserEntityService {
    
    ResponseEntity<String> signUp(Map<String, String> reuqestMap);
    
}
