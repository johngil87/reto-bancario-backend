package co.com.sofka.jpa.mapper;

import co.com.sofka.jpa.entities.ClienteEntity;
import co.com.sofka.jpa.entities.PersonaEntity;
import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.persona.Persona;

import java.util.UUID;

public class PersonaMapper {

    public static Cliente toData(PersonaEntity entity){
        return Cliente.builder()
                .identificacion(entity.getIdentificacion())
                .nombre(entity.getNombre())
                .direccion(entity.getDireccion())
                .estado(entity.getCliente().isEstado())
                .telefono(entity.getTelefono())
                .genero(entity.getGenero())
                .edad(entity.getEdad())
                .idCliente(entity.getCliente().getIdCliente())
                .contrasena(entity.getCliente().getContrasena())
                .build();
    }

    public static PersonaEntity toEntity(Cliente data){
        PersonaEntity newPerson = PersonaEntity.builder()
                .identificacion(data.getIdentificacion())
                .direccion(data.getDireccion())
                .edad(data.getEdad())
                .genero(data.getGenero())
                .nombre(data.getNombre())
                .telefono(data.getTelefono())
                .build();
        ClienteEntity newCliente = ClienteEntity.builder()
                .idCliente(null != data.getIdCliente()?data.getIdCliente():UUID.randomUUID().toString().replace("-","").substring(0,15))
                .idPersona(newPerson)
                .contrasena(data.getContrasena())
                .estado(data.isEstado())
                .build();
        newPerson.setCliente(newCliente);
        return newPerson;
    }

    public static ClienteEntity toClienteEntity(Cliente data){
        return ClienteEntity.builder()
                .idCliente(data.getIdCliente())
                .estado(data.isEstado())
                .contrasena(data.getContrasena())
                .build();
    }
}
