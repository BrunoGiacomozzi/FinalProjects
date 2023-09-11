
package arg.empleados.empleados.repository;

import arg.empleados.empleados.entity.Empleados;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosRepository extends CrudRepository<Empleados, Long> {
  
   
}
