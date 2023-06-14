package co.com.sofka.usecase.cuenta;

import co.com.sofka.model.cliente.gateways.ClienteRepository;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.cuenta.gateways.CuentaRepository;
import co.com.sofka.model.ex.BusinessExceptions;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CuentaUseCase {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public Mono<Cuenta> saveCuenta(Cuenta cuenta){
        return clienteRepository.getCliente(cuenta.getCliente().getIdCliente())
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()))
                .flatMap(item -> cuentaRepository.saveCuenta(cuenta));
    }

    public Mono<Cuenta> getCuenta(Integer id){
        return cuentaRepository.getCuenta(id)
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_ACOUNT.build()));
    }

    public Flux<Cuenta> getAll(){
        return cuentaRepository.getAll();
    }

    public Flux<Cuenta> getAllCuentas(List<Integer> ids){
        return cuentaRepository.getAllCuentasById(ids);
    }

    public Mono<Void> deleteCuenta(Integer id){
        return getCuenta(id)
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_ACOUNT.build()))
                .flatMap(item ->cuentaRepository.deleteCuenta(item.getNumeroCuenta()));
    }

    public Mono<Cuenta> updateCuenta(Cuenta cuenta){
        return clienteRepository.getCliente(cuenta.getCliente().getIdCliente())
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()))
                .flatMap(item-> getCuenta(cuenta.getNumeroCuenta()))
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_ACOUNT.build()))
                .flatMap(item-> cuentaRepository.updateCuenta(cuenta));
    }
}
