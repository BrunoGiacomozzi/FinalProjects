
package com.api.gestion.Api.gestion.facturas.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

 // vamos a crear un repository en dao, para hacer la query especial en el entity
//@NamedQuery(name="UserEntity.findByEmail",query = "select u from users u where u.email= :email;")
/*
La anotación @NamedQuery se utiliza para definir una consulta JPQL que se puede utilizar para recuperar datos de una base 
de datos. La anotación tiene dos argumentos:
El nombre de la consulta: Este es el nombre que se utilizará para hacer referencia a la consulta.
La consulta JPQL: Esta es la consulta que se ejecutará para recuperar los datos.
En el ejemplo que has compartido, la anotación @NamedQuery se utiliza para definir una consulta JPQL que se puede 
utilizar para recuperar un usuario por su dirección de correo electrónico.
*/

@Data
@Entity
@DynamicUpdate // Esta anotación indica a Hibernate que debe generar declaraciones SQL de actualización 
//que solo incluyan las columnas que han cambiado.
@DynamicInsert //  Esta anotación indica a Hibernate que debe generar declaraciones 
// SQL de inserción que solo incluyan las columnas que tienen valores no nulos.
@Table(name="users")
public class UserEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="contactNumber")
    private String contactNumber;
    
    @Column(name="email")
    private String email;
    
    @Column(name="password")
    private String password;
     
    @Column(name="status")
    private String status;
    
    @Column(name="rol")
    private String rol;
    
    // vamos a crear un repository en dao, para hac er la query especial
    
}
