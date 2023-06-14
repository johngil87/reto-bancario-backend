package co.com.sofka.usecase.movimiento;

import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.cuenta.gateways.CuentaRepository;
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
    private final MovimientosClienteRepository movimientosClienteRepository;
    private final String TYPEBEBIT = "debito";

    public Mono<Movimientos> saveMovimiento(Movimientos movimientos){
        return cuentaRepository.getCuenta(movimientos.getIdCuenta())
                .switchIfEmpty(Mono.error(new Exception("La cuenta no existe")))
                .flatMap(cuenta1 -> validarSaldoDebito(cuenta1, movimientos))
                .flatMap(cuenta2-> calcularSaldo(cuenta2, movimientos))
                .flatMap(movimientosRepository::saveMovimiento);
    }

    public  Mono<Movimientos> updateMovimiento(Movimientos movimientos){
        return movimientosRepository.updateMovimiento(movimientos);
    }

    public Mono<Movimientos> getMovimiento(Integer id){
        return  movimientosRepository.getMovimiento(id);
    }

    public Mono<Void> deleteMovimiento(Integer id){
        return movimientosRepository.deleteMovimiento(id);
    }

    public Flux<Movimientos> getListMovimientos(List<Integer> ids){
        return movimientosRepository.getListMovimientosByid(ids);
    }

    public Flux<MovimientosCliente> getListMovimientosCliente(String id, Date fechaInicial, Date fechaFinal){
        return movimientosClienteRepository.obtenerMovimientosClienteFecha(id,fechaInicial,fechaFinal);
    }

    private Mono<Movimientos> calcularSaldo(Cuenta cuenta, Movimientos newMove){
        if (newMove.getTipo().equals(TYPEBEBIT) ) {
            return restarSaldo(cuenta,newMove);
        }
        return sumarSaldo(cuenta,newMove);
    }

    private Mono<Cuenta> validarSaldoDebito(Cuenta cuenta, Movimientos newMove){
        if((newMove.getTipo().equals(TYPEBEBIT) && cuenta.getSaldoDisponible() == 0.0) ){
            return Mono.error(new Exception("saldo igual a cero"));
        }
        if (newMove.getTipo().equals(TYPEBEBIT) && cuenta.getSaldoDisponible() < newMove.getValor()) {
            return Mono.error(new Exception("saldo inferior a valor a retirar"));
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
