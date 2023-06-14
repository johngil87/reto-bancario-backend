package co.com.sofka.model.movimientoscliente;

import lombok.*;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class MovimientosCliente {

    private String tipo;
    private Double valor;
    private Double saldo;
    private Date fecha;
    private String nombre;
    private Double saldoInicial;
    private boolean estado;
    private Integer numeroCuenta;
    private String tipoCuenta;
}
