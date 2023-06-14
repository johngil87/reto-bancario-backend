package co.com.sofka.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class PersonaEntity {

    private String identificacion;
    private String direccion;
    private String nombre;
    private String genero;
    private Integer edad;
    private String telefono;
}
