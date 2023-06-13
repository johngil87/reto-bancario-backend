package co.com.sofka.usecase.cuenta;

import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.cuenta.gateways.CuentaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CuentaUseCase {

    private final CuentaRepository cuentaRepository;

    public Mono<Cuenta> saveCuenta(Cuenta cuenta){
        return cuentaRepository.saveCuenta(cuenta);
    }

    public Mono<Cuenta> getCuenta(Integer id){
        return cuentaRepository.getCuenta(id);
    }

    public Flux<Cuenta> getAllCuentas(){
        return cuentaRepository.getAllCuentas();
    }
}
