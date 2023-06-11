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
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @Column(name = "id_cliente")
    private String idCliente;
    private String contrasena;
    private boolean estado;
    @OneToOne
    @JoinColumn(name = "id_persona")
    private PersonaEntity idPersona;
}
