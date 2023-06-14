package co.com.sofka.model.movimientos.gateways;

import co.com.sofka.model.movimientos.Movimientos;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovimientosRepository {

    Mono<Movimientos> saveMovimiento(Movimientos movimientos);
    Mono<Movimientos> updateMovimiento(Movimientos movimientos);
    Mono<Movimientos> getMovimiento(Integer id);
    Mono<Movimientos> getlastMovimiento(Integer id);
    Mono<Void> deleteMovimiento(Integer id);
    Flux<Movimientos> getListMovimientosByid(List<Integer> ids);
}
