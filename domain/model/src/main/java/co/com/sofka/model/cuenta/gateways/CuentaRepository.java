package co.com.sofka.model.cuenta.gateways;

import co.com.sofka.model.cuenta.Cuenta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CuentaRepository {

    Mono<Cuenta> saveCuenta(Cuenta cuenta);
    Mono<Cuenta> getCuenta(Integer id);
    Flux<Cuenta> getAllCuentasById(List<Integer> ids);
    Mono<Void> deleteCuenta(Integer id);
    Mono<Cuenta> updateCuenta(Cuenta cuenta);
}
