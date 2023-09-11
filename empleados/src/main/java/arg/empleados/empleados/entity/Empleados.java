
package arg.empleados.empleados.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empleados")
public class Empleados {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;
    
    @Column(name="dni")
    private Long dni;
    
    @Column(name="nombre")
    private String nombre;
   
    @Email
    @Column(name="email")
    private String email;
    
    @Column(name="cuit")
    private String cuit;
    
    @Column(name="ciudad")
    private String ciudad;
    
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
    
    @Column(name="sueldo")
    private Double sueldo;
    
}
