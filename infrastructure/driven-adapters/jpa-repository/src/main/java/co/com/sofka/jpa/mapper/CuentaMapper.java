package co.com.sofka.jpa.mapper;

import co.com.sofka.jpa.entities.CuentaEntity;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.movimientos.Movimientos;

import java.util.List;

public class CuentaMapper {

    public static CuentaEntity toEntity(Cuenta cuenta){
        return  CuentaEntity.builder()
                .estado(cuenta.isEstado())
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .numeroCuenta(null != cuenta.getNumeroCuenta()?cuenta.getNumeroCuenta():null)
                .idCliente(PersonaMapper.toClienteEntity(cuenta.getCliente()))
                .build();
    }

    public static Cuenta toData(CuentaEntity entity){
        return  Cuenta.builder()
                .estado(entity.isEstado())
                .tipoCuenta(entity.getTipoCuenta())
                .saldoInicial(entity.getSaldoInicial())
                .numeroCuenta(null != entity.getNumeroCuenta()?entity.getNumeroCuenta():null)
                //.idCliente(PersonaMapper.toClienteEntity(entity.getCliente()))
                //.cliente(PersonaMapper.toData(entity.getIdCliente()))
                .build();
    }
}
