package co.com.sofka.jpa.mapper;

import co.com.sofka.jpa.entities.ClienteEntity;
import co.com.sofka.model.cliente.Cliente;

public class ClienteMapper {

    public static Cliente toData(ClienteEntity entity){
        return Cliente.builder()
                .identificacion(entity.getIdentificacion())
                .nombre(entity.getNombre())
                .direccion(entity.getDireccion())
                .estado(entity.isEstado())
                .telefono(entity.getTelefono())
                .genero(entity.getGenero())
                .edad(entity.getEdad())
                .contrasena(entity.getContrasena())
                .build();
    }

    public static ClienteEntity toEntity(Cliente data){
        return ClienteEntity.builder()
                .identificacion(data.getIdentificacion())
                .direccion(data.getDireccion())
                .edad(data.getEdad())
                .genero(data.getGenero())
                .nombre(data.getNombre())
                .telefono(data.getTelefono())
                .contrasena(data.getContrasena())
                .estado(data.isEstado())
                .build();
    }

}
