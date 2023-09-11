
package arg.empleados.empleados.repository;

import arg.empleados.empleados.entity.Sucursal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends CrudRepository<Sucursal, Long>{
    
}
