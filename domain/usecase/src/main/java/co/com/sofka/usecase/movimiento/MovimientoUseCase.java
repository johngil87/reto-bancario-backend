package co.com.sofka.usecase.movimiento;

import co.com.sofka.model.movimientos.Movimientos;
import co.com.sofka.model.movimientos.gateways.MovimientosRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MovimientoUseCase {

    private final MovimientosRepository movimientosRepository;

    public Mono<Movimientos> saveMovimiento(Movimientos movimientos){
        return movimientosRepository.saveMovimiento(movimientos);
    }
}
