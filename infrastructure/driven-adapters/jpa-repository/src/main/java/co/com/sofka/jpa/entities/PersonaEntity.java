package co.com.sofka.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "persona")
public class PersonaEntity {

    @Id
    private String identificacion;
    private String direccion;
    private String nombre;
    private String genero;
    private Integer edad;
    private String telefono;
    @OneToOne(mappedBy = "idPersona", cascade = CascadeType.ALL)
    private ClienteEntity cliente;
}
