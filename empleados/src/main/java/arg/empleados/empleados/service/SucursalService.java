package arg.empleados.empleados.service;

import arg.empleados.empleados.entity.Empleados;
import arg.empleados.empleados.entity.Sucursal;
import arg.empleados.empleados.repository.SucursalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {
    
    @Autowired
    private SucursalRepository sucursalRepo;
    
    public Sucursal findById(Long id){
        return sucursalRepo.findById(id).orElse(null);
    }
    
    public List<Sucursal> findAll(){
        return (List<Sucursal>) sucursalRepo.findAll();
    }
    
    public void deleteSucural(Sucursal sucursal){
         sucursalRepo.delete(sucursal);
    }
    
    public void deleteByIdSucursal(Long id){
        sucursalRepo.deleteById(id);
    }
    
    public Sucursal completeFormSucursal(List<Empleados> empleados, String ubicacion){
        var sucursal = new Sucursal();
        
        sucursal.setEmpleadosSucursal(empleados);
        sucursal.setUbicacion(ubicacion);
        
        return sucursalRepo.save(sucursal);
    }
    
    public Sucursal incompleteFormSucursal( String ubicacion){
        var sucursal = new Sucursal();
        
        
        sucursal.setUbicacion(ubicacion);
        
        return sucursalRepo.save(sucursal);
    }
}
