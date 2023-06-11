package co.com.sofka.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "id_persona")
    private String idPersona;
}
