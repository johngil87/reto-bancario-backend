package co.com.sofka.jpa.cuenta;

import co.com.sofka.jpa.entities.CuentaEntity;
import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.jpa.mapper.CuentaMapper;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.cuenta.gateways.CuentaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Repository
public class CuentaAdapter extends AdapterOperations<Cuenta, CuentaEntity,Integer, CuentaJpaRepository> implements CuentaRepository {

    public CuentaAdapter(CuentaJpaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d,Cuenta.CuentaBuilder.class).build());
    }

    @Override
    public Mono<Cuenta> saveCuenta(Cuenta cuenta) {
        return Mono.just(CuentaMapper.toData(repository.save(CuentaMapper.toEntity(cuenta))));
    }

    @Override
    public Mono<Cuenta> getCuenta(Integer id) {
        return repository.findById(id).map(item -> Mono.just(CuentaMapper.toData(item))).orElse(Mono.empty());
    }

    @Override
    public Flux<Cuenta> getAllCuentasById(List<Integer> ids) {
        return Flux.fromIterable(CuentaMapper.toDataList(repository.findAllById(ids)));
    }

    @Override
    public Mono<Void> deleteCuenta(Integer id) {
        CuentaEntity cuenta =repository.findById(id).get();
        repository.delete(cuenta);
        return Mono.empty();
    }

    @Override
    public Mono<Cuenta> updateCuenta(Cuenta cuenta) {
        return Mono.just(CuentaMapper.toData(repository.save(CuentaMapper.toEntity(cuenta))));
    }
}
