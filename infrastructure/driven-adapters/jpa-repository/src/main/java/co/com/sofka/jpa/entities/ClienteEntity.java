package co.com.sofka.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "cliente")
public class ClienteEntity extends PersonaEntity{

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClitente;
    @NotBlank
    private String contrasena;
    private boolean estado;
    @OneToMany(mappedBy = "idCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CuentaEntity> cuentas;
}
