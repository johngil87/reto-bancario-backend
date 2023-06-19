package co.com.sofka.usecase.movimiento;

import co.com.sofka.model.cliente.gateways.ClienteRepository;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.cuenta.gateways.CuentaRepository;
import co.com.sofka.model.cuenta.gateways.CuentasClienteRepository;
import co.com.sofka.model.ex.BusinessExceptions;
import co.com.sofka.model.movimientos.Movimientos;
import co.com.sofka.model.movimientoscliente.MovimientosCliente;
import co.com.sofka.model.movimientos.gateways.MovimientosRepository;
import co.com.sofka.model.movimientoscliente.gateways.MovimientosClienteRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class MovimientoUseCase {

    private final MovimientosRepository movimientosRepository;
    private final CuentaRepository cuentaRepository;
    private final CuentasClienteRepository cuentasClienteRepository;
    private final ClienteRepository clienteRepository;
    private final MovimientosClienteRepository movimientosClienteRepository;
    private final String TYPEBEBIT = "debito";

    public Mono<Movimientos> saveMovimiento(Movimientos movimientos){
        return cuentasClienteRepository.getCuentaCLiente(movimientos.getIdentificacion(), movimientos.getContrasena(), movimientos.getIdCuenta())
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT_PASSWORD.build()))
                .flatMap(cuenta1 -> validarSaldoDebito(cuenta1, movimientos))
                .flatMap(cuenta2-> calcularSaldo(cuenta2, movimientos))
                .flatMap(movimientosRepository::saveMovimiento);
    }

    public  Mono<Movimientos> updateMovimiento(Movimientos movimientos){
        return getMovimiento(movimientos.getIdMovimiento())
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_MOVE.build()))
                .flatMap(item-> movimientosRepository.updateMovimiento(movimientos));
    }

    public Mono<Movimientos> getMovimiento(Integer id){
        return  movimientosRepository.getMovimiento(id)
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_MOVE.build()));
    }

    public Mono<Void> deleteMovimiento(Integer id){
        return movimientosRepository.deleteMovimiento(id)
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_MOVE.build()));
    }

    public Flux<Movimientos> getListMovimientos(List<Integer> ids){
        return movimientosRepository.getListMovimientosByid(ids);
    }

    public Flux<MovimientosCliente> getListMovimientosCliente(Integer id, Date fechaInicial, Date fechaFinal){
        return  clienteRepository.getCliente(id)
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()))
                .flux()
                .flatMap(item-> movimientosClienteRepository.obtenerMovimientosClienteFecha(id,fechaInicial,fechaFinal));
    }

    public Flux<Movimientos> getAllMovimientos(){
        return movimientosRepository.getAll();
    }

    private Mono<Movimientos> calcularSaldo(Cuenta cuenta, Movimientos newMove){
        if (newMove.getTipo().equals(TYPEBEBIT) ) {
            return restarSaldo(cuenta,newMove);
        }
        return sumarSaldo(cuenta,newMove);
    }

    private Mono<Cuenta> validarSaldoDebito(Cuenta cuenta, Movimientos newMove){
        if((newMove.getTipo().equals(TYPEBEBIT) && cuenta.getSaldoDisponible() == 0.0) ){
            return Mono.error(BusinessExceptions.Type.INVALID_ID_BALANCE_CERO.build());
        }
        if (newMove.getTipo().equals(TYPEBEBIT) && cuenta.getSaldoDisponible() < newMove.getValor()) {
            return Mono.error(BusinessExceptions.Type.INVALID_ID_BALANCE.build());
        }
        return Mono.just(cuenta);
    }

    private Mono<Movimientos> sumarSaldo(Cuenta cuenta, Movimientos newMove){
        newMove.setSaldo(cuenta.getSaldoDisponible() + newMove.getValor());
        cuenta.setSaldoDisponible(cuenta.getSaldoDisponible() + newMove.getValor());
        actualizarCuenta(cuenta);
        return Mono.just(newMove);
    }

    private Mono<Movimientos> restarSaldo(Cuenta cuenta, Movimientos newMove){
        newMove.setSaldo(cuenta.getSaldoDisponible() - newMove.getValor());
        cuenta.setSaldoDisponible(cuenta.getSaldoDisponible() - newMove.getValor());
        actualizarCuenta(cuenta);
        return Mono.just(newMove);
    }

    private void actualizarCuenta(Cuenta cuenta){
        cuentaRepository.updateCuenta(cuenta).subscribe();
    }
}
