package co.com.sofka.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class MovimientosClienteEntity {

    @Id
    @Column(name = "IDMOVIMIENTO")
    private Integer numeroMovimiento;
    @Column(name = "NUMEROCUENTA")
    private Integer numeroCuenta;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "VALOR")
    private Double valor;
    @Column(name = "SALDO")
    private Double saldo;
    @Column(name = "FECHA")
    private Date fecha;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "SALDOINICIAL")
    private Double saldoInicial;
    @Column(name = "ESTADO")
    private boolean estado;
    @Column(name = "TIPOCUENTA")
    private String tipoCuenta;
}
