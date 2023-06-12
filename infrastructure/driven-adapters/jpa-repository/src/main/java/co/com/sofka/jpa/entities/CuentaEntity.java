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
public class CuentaEntity {

    @Id
    @Column(name = "numero_cuenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numeroCuenta;
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    private boolean estado;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity idCliente;
    @OneToMany(mappedBy = "idCuenta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MovimientosEntity> movimientos;
}
