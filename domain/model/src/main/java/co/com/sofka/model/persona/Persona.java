package co.com.sofka.model.persona;
import lombok.*;
import lombok.experimental.SuperBuilder;
//import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Persona {

    private String identificacion;
    private String direccion;
    private String nombre;
    private String genero;
    private Integer edad;
    private String telefono;
}
