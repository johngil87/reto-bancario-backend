package co.com.sofka.model.cuenta;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.movimientos.Movimientos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Cuenta {

    private Integer numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private boolean estado;
    private Cliente cliente;
    private List<Movimientos> movimientos;
}
