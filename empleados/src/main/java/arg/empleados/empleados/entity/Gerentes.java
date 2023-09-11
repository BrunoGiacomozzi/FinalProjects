
package arg.empleados.empleados.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gerentes")
public class Gerentes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGerente;
    
    private String nombre;
    
    private String cargo;
    
   @OneToOne
   @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
    
}
