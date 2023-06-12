package co.com.sofka.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "cliente")
public class MovimientosEntity {

    @Id
    @Column(name = "id_movimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;
    private Date fecha;
    private String tipo;
    private Double valor;
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private CuentaEntity idCuenta;
}
