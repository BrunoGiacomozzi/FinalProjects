package arg.empleados.empleados.controller;

import arg.empleados.empleados.entity.Empleados;
import arg.empleados.empleados.entity.Gerentes;
import arg.empleados.empleados.entity.Sucursal;
import arg.empleados.empleados.service.EmpleadosService;
import arg.empleados.empleados.service.GerenteService;
import arg.empleados.empleados.service.SucursalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

    @Autowired
    private EmpleadosService empleadoS;

    @Autowired
    private SucursalService sucursalS;
    
    @Autowired
    private GerenteService gerenteS;

    @GetMapping("/empleados")
    public List<Empleados> getList() {
        return empleadoS.allEmloyees();
    }

    @PostMapping("/empleados/crearempcomp")
    public Empleados crearEmpleadoCompleto(@RequestBody String nombre, Long id, String email,
            String cuit, String ciudad) {

        return empleadoS.completeFormEmployee(nombre, Long.MIN_VALUE, email, cuit, ciudad);
    }

    @PostMapping("/empleados/crearempinco")
    public Empleados crearEmpleadoIncompleto(@RequestBody String nombre, Long id, String email) {

        return empleadoS.IcompleteFormEmployee(nombre, id, email);
    }

    @PostMapping("/empleados/crearsuccomp")
    public Sucursal crearSucursalComp(@RequestBody List<Empleados> empleados, String ubicacion) {

        return sucursalS.completeFormSucursal(empleados, ubicacion);
  }
    
    @PostMapping("/empleados/crearsucinco")
    public Sucursal crearSucursalIncomp(@RequestBody String ubiccacion) {

        return sucursalS.incompleteFormSucursal(ubiccacion);
  }
    
    @PostMapping("/empleados/creargercomp")
    public Gerentes crearGerentesComp(String nombre, String cargo, String ubicacion){
        return gerenteS.completeFormGerente(nombre, cargo, ubicacion);
    }
    
    @PostMapping("/empleados/creargerinco")
    public Gerentes crearGerentesIncoomp(String nombre, String cargo){
        return gerenteS.incopleteFormGerente(nombre, cargo);
    }
    
    
}
