package co.com.sofka.model.cuenta.gateways;

import co.com.sofka.model.cuenta.Cuenta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentasClienteRepository {

    Flux<Cuenta> getAllCuentasCliente(String id);
    Mono<Cuenta> getCuentaCLiente(String id, String password, Integer idCuenta);
}
