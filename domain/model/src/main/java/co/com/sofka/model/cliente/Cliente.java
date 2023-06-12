package co.com.sofka.model.cliente;
import co.com.sofka.model.persona.Persona;
import lombok.*;
import lombok.experimental.SuperBuilder;
//import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Cliente extends Persona {

    private String idCliente;
    private String contrasena;
    private boolean estado;
}
