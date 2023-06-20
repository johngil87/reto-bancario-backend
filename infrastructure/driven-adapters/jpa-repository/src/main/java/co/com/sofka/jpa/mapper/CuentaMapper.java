package co.com.sofka.jpa.mapper;

import co.com.sofka.jpa.entities.ClienteEntity;
import co.com.sofka.jpa.entities.CuentaCliente;
import co.com.sofka.jpa.entities.CuentaEntity;
import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.movimientos.Movimientos;

import java.util.ArrayList;
import java.util.List;

public class CuentaMapper {

    public static CuentaEntity toEntity(Cuenta cuenta){
        CuentaEntity newCuenta =  CuentaEntity.builder()
                .estado(cuenta.isEstado())
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .saldoDisponible(null != cuenta.getSaldoDisponible()?cuenta.getSaldoDisponible():cuenta.getSaldoInicial())
                .numeroCuenta(null != cuenta.getNumeroCuenta()?cuenta.getNumeroCuenta():null)
                .build();
        ClienteEntity cliente = ClienteEntity.builder()
                .identificacion(cuenta.getCliente().getIdentificacion())
                .estado(cuenta.getCliente().isEstado())
                .contrasena(cuenta.getCliente().getContrasena())
                .direccion(cuenta.getCliente().getDireccion())
                .edad(cuenta.getCliente().getEdad())
                .genero(cuenta.getCliente().getGenero())
                .nombre(cuenta.getCliente().getNombre())
                .telefono(cuenta.getCliente().getTelefono())
                .idClitente(null != cuenta.getCliente().getIdCliente()?cuenta.getCliente().getIdCliente(): null)
                .build();
        newCuenta.setIdCliente(cliente);
        return newCuenta;
    }

    public static Cuenta toData(CuentaEntity entity){
        return  Cuenta.builder()
                .estado(entity.isEstado())
                .tipoCuenta(entity.getTipoCuenta())
                .saldoInicial(entity.getSaldoInicial())
                .numeroCuenta(entity.getNumeroCuenta())
                .saldoDisponible(entity.getSaldoDisponible())
                .cliente(ClienteMapper.toData(entity.getIdCliente()))
                .movimientos(MovimientoMapper.toListData(entity.getMovimientos()))
                .build();
    }

    public static Cuenta toDataMov(CuentaEntity entity){
        return  Cuenta.builder()
                .estado(entity.isEstado())
                .tipoCuenta(entity.getTipoCuenta())
                .saldoInicial(entity.getSaldoInicial())
                .numeroCuenta(entity.getNumeroCuenta())
                .saldoDisponible(entity.getSaldoDisponible())
                .cliente(ClienteMapper.toData(entity.getIdCliente()))
                .build();
    }

    public static List<Cuenta> toDataList(Iterable<CuentaEntity> entityList){
        List<Cuenta> cuentaList= new ArrayList<>();
        entityList.forEach(item -> cuentaList.add(toData(item)));
        return cuentaList;
    }

    public static Cuenta toData(CuentaCliente entity){
        Cliente cliente = Cliente.builder()
                .idCliente(entity.getIdCliente())
                .identificacion(entity.getIdentificacion())
                .direccion(entity.getDireccion())
                .edad(entity.getEdad())
                .genero(entity.getGenero())
                .contrasena(entity.getContrasena())
                .nombre(entity.getNombres())
                .estado(entity.isEstado())
                .telefono(entity.getTelefono())
                .build();
        return Cuenta.builder()
                .numeroCuenta(entity.getNumeroCuenta())
                .tipoCuenta(entity.getTipoCuenta())
                .saldoInicial(entity.getSaldoInicial())
                .estado(entity.isEstado())
                .saldoDisponible(entity.getSaldoDisponible())
                .cliente(cliente)
                .build();

    }

    public static Cuenta toDataClient(CuentaCliente entity){
        return Cuenta.builder()
                .numeroCuenta(entity.getNumeroCuenta())
                .tipoCuenta(entity.getTipoCuenta())
                .saldoInicial(entity.getSaldoInicial())
                .estado(entity.isEstado())
                .saldoDisponible(entity.getSaldoDisponible())
                .build();

    }

    public static List<Cuenta> toDataCLientList(Iterable<CuentaCliente> entityList){
        List<Cuenta> cuentaList= new ArrayList<>();
        entityList.forEach(item -> cuentaList.add(toDataClient(item)));
        return cuentaList;
    }

    public static List<Cuenta> toDataCLien(Iterable<CuentaCliente> entityList){
        List<Cuenta> cuentaList= new ArrayList<>();
        entityList.forEach(item -> cuentaList.add(toData(item)));
        return cuentaList;
    }
}
