
package arg.empleados.empleados.repository;

import arg.empleados.empleados.entity.Gerentes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerentesRepository  extends CrudRepository<Gerentes, Long>{
    
}
