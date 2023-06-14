package co.com.sofka.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    private String identificacion;
    private String direccion;
    private String nombre;
    private String genero;
    private Integer edad;
    private String telefono;
    private String contrasena;
    private boolean estado;
    @OneToMany(mappedBy = "idCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CuentaEntity> cuentas;
}
