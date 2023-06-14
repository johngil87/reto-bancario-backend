package co.com.sofka.jpa.movimientos;

import co.com.sofka.jpa.entities.MovimientosEntity;
import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.jpa.mapper.MovimientoMapper;
import co.com.sofka.model.movimientos.Movimientos;
import co.com.sofka.model.movimientos.gateways.MovimientosRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class MovimientosAdapter extends AdapterOperations<Movimientos,MovimientosEntity, Integer, MovimientosJpaRepository> implements MovimientosRepository {

    public MovimientosAdapter(MovimientosJpaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Movimientos.MovimientosBuilder.class).build());
    }

    @Override
    public Mono<Movimientos> saveMovimiento(Movimientos movimientos) {
        return Mono.just(MovimientoMapper.toData(repository.save(MovimientoMapper.toEntity(movimientos))));
    }

    @Override
    public Mono<Movimientos> updateMovimiento(Movimientos movimientos) {
        return Mono.just(MovimientoMapper.toData(repository.save(MovimientoMapper.toEntity(movimientos))));
    }

    @Override
    public Mono<Movimientos> getMovimiento(Integer id) {
        return repository.findById(id).map(mov ->Mono.just(MovimientoMapper.toData(mov))).orElse(Mono.empty());
    }

    @Override
    public Mono<Movimientos> getlastMovimiento(Integer id) {
        MovimientosEntity oldMove= repository.findLastMovedByIdAcound(id);
        if(null == oldMove){
            return  Mono.empty();
        }
        return  Mono.just(MovimientoMapper.toData(repository.findLastMovedByIdAcound(id)));
    }

    @Override
    public Mono<Void> deleteMovimiento(Integer id) {
        repository.deleteById(id);
        return Mono.empty();
    }

    @Override
    public Flux<Movimientos> getListMovimientosByid(List<Integer> ids) {
        return Flux.fromIterable(MovimientoMapper.toListData(repository.findAllById(ids)));
    }
}
