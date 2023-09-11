package arg.empleados.empleados.service;

import arg.empleados.empleados.entity.Gerentes;
import arg.empleados.empleados.entity.Sucursal;
import arg.empleados.empleados.repository.GerentesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    
public class GerenteService {
    
    @Autowired
    private GerentesRepository gerenteRepo;
    
    public Gerentes findById(Long id){
        return gerenteRepo.findById(id).orElse(null);
    }
    
    public List<Gerentes> AllfindGerentes(){
        return (List<Gerentes>) gerenteRepo.findAll();
    }
    
    public void deleteById(Long id){
      gerenteRepo.deleteById(id);
    }
    
    public void AllfindGerentes(Gerentes gerente){
        gerenteRepo.delete(gerente);
    }
    
    public Gerentes completeFormGerente(String nombre, String cargo, String ubicacion){
        Gerentes gerente = new Gerentes();
        Sucursal sucursal = new Sucursal();
        gerente.setNombre(nombre);
        gerente.setCargo(cargo);
        
        sucursal.setUbicacion(ubicacion);
        gerente.setSucursal(sucursal);
        
        return gerenteRepo.save(gerente);
    }
    
     public Gerentes incopleteFormGerente(String nombre, String cargo){
        Gerentes gerente = new Gerentes();
        
        gerente.setNombre(nombre);
        gerente.setCargo(cargo);
        
        
        return gerenteRepo.save(gerente);
    }
    
}
