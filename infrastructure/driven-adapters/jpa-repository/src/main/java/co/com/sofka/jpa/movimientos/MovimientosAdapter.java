package co.com.sofka.jpa.movimientos;

import co.com.sofka.jpa.entities.MovimientosEntity;
import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.movimientos.Movimientos;
import co.com.sofka.model.movimientos.gateways.MovimientosRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Repository
public class MovimientosAdapter extends AdapterOperations<Movimientos,MovimientosEntity, Integer, MovimientosJpaRepository> implements MovimientosRepository {

    public MovimientosAdapter(MovimientosJpaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Movimientos.MovimientosBuilder.class).build());
    }

    @Override
    public Mono<Movimientos> saveMovimiento(Movimientos movimientos) {
        return null;
    }

    @Override
    public Flux<Movimientos> getListMovimientosByid(String id) {
        return null;
    }
}
