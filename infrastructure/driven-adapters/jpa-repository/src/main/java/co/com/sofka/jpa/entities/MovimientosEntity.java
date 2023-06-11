package co.com.sofka.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer idMovimiento;
    private Date fecha;
    private String tipo;
    private Double valor;
    private Double saldo;
    @Column(name = "id_cuenta")
    private String idCuenta;
}
