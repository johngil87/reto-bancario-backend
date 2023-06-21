package co.com.sofka.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "cuenta")
public class CuentaEntity {

    @Id
    @Column(name = "numero_cuenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numeroCuenta;
    @Column(name = "tipo_cuenta")
    @NotBlank
    private String tipoCuenta;
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    @Column(name = "saldo_disponible")
    private Double saldoDisponible;
    private boolean estado;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity idCliente;
    @OneToMany(mappedBy = "idCuenta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MovimientosEntity> movimientos;
}
