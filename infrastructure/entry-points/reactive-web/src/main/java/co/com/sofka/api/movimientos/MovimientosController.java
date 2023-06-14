package co.com.sofka.api.movimientos;

import co.com.sofka.model.movimientos.Movimientos;
import co.com.sofka.model.movimientoscliente.MovimientosCliente;
import co.com.sofka.usecase.movimiento.MovimientoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movimientos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MovimientosController {

    private final MovimientoUseCase movimientoUseCase;

    @PostMapping
    public Mono<Movimientos> guardarMovimientos(@RequestBody Movimientos movimientos){
        return movimientoUseCase.saveMovimiento(movimientos);
    }

    @PutMapping
    public Mono<Movimientos> actualizarMoviomiento(@RequestBody Movimientos movimientos){
        return movimientoUseCase.updateMovimiento(movimientos);
    }

    @GetMapping("/id")
    public Mono<Movimientos> obtenerMovimientos(@RequestParam Integer id){
        return movimientoUseCase.getMovimiento(id);
    }

    @GetMapping
    public Flux<Movimientos> obtenerMovimientos(){
        return movimientoUseCase.getAllMovimientos();
    }


    @GetMapping("/cliente")
    public Flux<MovimientosCliente> obtenerMovimientosPorClienteRangoFechas(@RequestParam("id") Integer id, @RequestParam("fechaInicial") Date fechaInicial, @RequestParam("fechaFinal") Date fechaFinal ){
        return movimientoUseCase.getListMovimientosCliente(id, fechaInicial, fechaFinal);
    }

    @GetMapping("/listId")
    public Flux<Movimientos> obtenerListaMovimientos(@RequestParam List<Integer> ids){
        return movimientoUseCase.getListMovimientos(ids);
    }

    @DeleteMapping
    public Mono<Void> eliminarMovimientos(@RequestParam Integer id){
        return movimientoUseCase.deleteMovimiento(id);
    }
}
