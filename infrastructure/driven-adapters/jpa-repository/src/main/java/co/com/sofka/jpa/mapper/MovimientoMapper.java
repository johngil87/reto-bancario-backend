package co.com.sofka.jpa.mapper;

import co.com.sofka.jpa.entities.MovimientosClienteEntity;
import co.com.sofka.jpa.entities.MovimientosEntity;
import co.com.sofka.model.movimientos.Movimientos;
import co.com.sofka.model.movimientoscliente.MovimientosCliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoMapper {

    public static MovimientosEntity toEntity(Movimientos data){
        return MovimientosEntity.builder()
                .idMovimiento(null != data.getIdMovimiento() ? data.getIdMovimiento() : null)
                .fecha(null != data.getFecha() ? data.getFecha() : new Date())
                .saldo(null != data.getSaldo() ? data.getSaldo() : 0.0)
                .tipo(data.getTipo())
                .valor(data.getValor())
                .idCuenta(data.getIdCuenta())
                .build();
    }

    public static Movimientos toData(MovimientosEntity entity1){
        return Movimientos.builder()
                .idMovimiento(entity1.getIdMovimiento())
                .fecha(entity1.getFecha())
                .saldo(entity1.getSaldo())
                .valor(entity1.getValor())
                .tipo(entity1.getTipo())
                .idCuenta(entity1.getIdCuenta())
                .build();
    }

    public static List<Movimientos> toListData(Iterable<MovimientosEntity> entityList){
        List<Movimientos> list = new ArrayList<>();
        entityList.forEach(item -> list.add(toData(item)));
        return list;
    }

    public static MovimientosCliente toData(MovimientosClienteEntity entity){
        return MovimientosCliente.builder()
                .estado(entity.isEstado())
                .nombre(entity.getNombre())
                .numeroCuenta(entity.getNumeroCuenta())
                .fecha(entity.getFecha())
                .saldoInicial(entity.getSaldoInicial())
                .tipoCuenta(entity.getTipoCuenta())
                .saldo(entity.getSaldo())
                .tipo(entity.getTipo())
                .valor(entity.getValor())
                .build();
    }


    public static List<MovimientosCliente> tolistData(List<MovimientosClienteEntity> entityList){
        List<MovimientosCliente> list = new ArrayList<>();
        entityList.forEach(item-> list.add(toData(item)));
        return list;
    }
}
