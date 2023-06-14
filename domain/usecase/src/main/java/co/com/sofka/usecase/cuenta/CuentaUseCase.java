package co.com.sofka.usecase.cuenta;

import co.com.sofka.model.cliente.gateways.ClienteRepository;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.cuenta.gateways.CuentaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CuentaUseCase {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public Mono<Cuenta> saveCuenta(Cuenta cuenta){
        return clienteRepository.getCliente(cuenta.getCliente().getIdentificacion())
                .switchIfEmpty(Mono.error(new Exception("Cliente no existe")))
                .flatMap(item -> cuentaRepository.saveCuenta(cuenta));
    }

    public Mono<Cuenta> getCuenta(Integer id){
        return cuentaRepository.getCuenta(id)
                .switchIfEmpty(Mono.error(new Exception("Cuenta no existe")));
    }

    public Flux<Cuenta> getAllCuentas(List<Integer> ids){
        return cuentaRepository.getAllCuentasById(ids);
    }

    public Mono<Void> deleteCuenta(Integer id){
        return cuentaRepository.deleteCuenta(id)
                .onErrorResume(Mono::error);
    }

    public Mono<Cuenta> updateCuenta(Cuenta cuenta){
        return getCuenta(cuenta.getNumeroCuenta())
                .flatMap(item-> cuentaRepository.updateCuenta(cuenta))
                .onErrorResume(Mono::error);
    }
}
