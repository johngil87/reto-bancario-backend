package co.com.sofka.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "movimientos")
public class MovimientosEntity {

    @Id
    @Column(name = "id_movimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;
    private Date fecha;
    @NotBlank
    private String tipo;
    private Double valor;
    private Double saldo;
    @Column(name = "id_cuenta")
    private Integer idCuenta;
}
