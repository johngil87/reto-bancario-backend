package co.com.sofka.model.movimientos.gateways;

import co.com.sofka.model.movimientos.Movimientos;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovimientosRepository {

    Mono<Movimientos> saveMovimiento(Movimientos movimientos);
    Flux<Movimientos> getListMovimientosByid(String id);
}
