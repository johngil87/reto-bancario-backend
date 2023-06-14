package co.com.sofka.model.movimientoscliente.gateways;

import co.com.sofka.model.movimientoscliente.MovimientosCliente;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface MovimientosClienteRepository {

    Flux<MovimientosCliente> obtenerMovimientosClienteFecha(Integer id, Date fechaInicial, Date fechaFinal);
}
