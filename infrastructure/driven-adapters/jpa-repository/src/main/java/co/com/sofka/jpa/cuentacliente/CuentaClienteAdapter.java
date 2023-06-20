package co.com.sofka.jpa.cuentacliente;

import co.com.sofka.jpa.entities.CuentaCliente;
import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.jpa.mapper.CuentaMapper;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.cuenta.gateways.CuentasClienteRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Repository
public class CuentaClienteAdapter extends AdapterOperations<Cuenta, CuentaCliente, Integer, CuentaClienteJpaRepository> implements CuentasClienteRepository {

    protected CuentaClienteAdapter(CuentaClienteJpaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Cuenta.CuentaBuilder.class).build());
    }

    @Override
    public Flux<Cuenta> getAllCuentasCliente(String id) {
        return Flux.fromIterable(CuentaMapper.toDataCLientList(repository.listCuentaClient(id)))
                .onErrorResume(it -> Flux.empty());
    }

    @Override
    public Mono<Cuenta> getCuentaCLiente(String id, String password, Integer idCuenta) {
        return Flux.fromIterable(CuentaMapper.toDataCLien(repository.cuentaClient(id,password,idCuenta)))
                .next()
                .onErrorResume(it-> Mono.empty());
    }
}
