package co.com.sofka.model.movimientos;

import co.com.sofka.model.cuenta.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Movimientos {

    private Integer idMovimiento;
    private Date fecha;
    private String tipo;
    private Double valor;
    private Double saldo;
    private String idCLiente;
    private Integer idCuenta;
}
