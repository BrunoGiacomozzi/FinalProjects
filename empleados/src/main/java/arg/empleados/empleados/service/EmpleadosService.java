
package arg.empleados.empleados.service;

import arg.empleados.empleados.entity.Empleados;
import arg.empleados.empleados.repository.EmpleadosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadosService {
    
    @Autowired
    private EmpleadosRepository empladosRepo;
    
    public Empleados savedEmployee(Empleados empleado){
        
        return empladosRepo.save(empleado);
    }
    
    public Empleados findById(Long id){
        return empladosRepo.findById(id).orElse(null);
    }
    
    public List<Empleados> allEmloyees(){
        return (List<Empleados>) empladosRepo.findAll();
    }
    
    public void eliminateEmployee(Empleados empleados){
       empladosRepo.delete(empleados);
    }
    
    public void deleteById(Long id){
      empladosRepo.deleteById(id);
    }
    
    
    public Empleados completeFormEmployee(String nombre, Long dni, String email,
            String cuit, String ciudad){
        
        Empleados empleado = new Empleados();
        
        empleado.setNombre(nombre);
        empleado.setDni(dni);
        empleado.setEmail(email);
        empleado.setCuit(cuit);
        empleado.setCiudad(ciudad);
        
        return empladosRepo.save(empleado);
    }
    
    public Empleados IcompleteFormEmployee(String nombre, Long dni, String email){
        
        Empleados empleado = new Empleados();
        
        empleado.setNombre(nombre);
        empleado.setDni(dni);
        empleado.setEmail(email);
        
        
        return empladosRepo.save(empleado);
    }
    
    
}
