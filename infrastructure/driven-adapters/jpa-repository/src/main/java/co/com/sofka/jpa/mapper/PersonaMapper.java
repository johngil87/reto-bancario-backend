package co.com.sofka.jpa.mapper;

import co.com.sofka.jpa.entities.ClienteEntity;
import co.com.sofka.jpa.entities.PersonaEntity;
import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.persona.Persona;

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
                .build();
    }

    public static PersonaEntity toEntity(Cliente data){
        PersonaEntity newPerson = PersonaEntity.builder()
                .cliente(toClienteEntity(data))
                .direccion(data.getDireccion())
                .edad(data.getEdad())
                .genero(data.getGenero())
                .nombre(data.getNombre())
                .telefono(data.getTelefono())
                .build();
        ClienteEntity newCliente = ClienteEntity.builder()
                .idPersona(newPerson)
                .contrasena(data.getContrasena())
                .estado(data.isEstado())
                .build();
        if (null != data.getIdentificacion()) {
            newPerson.setIdentificacion(data.getIdentificacion());
            newCliente.setIdCliente(data.getIdCliente());
        }
        newPerson.setCliente(newCliente);
        return newPerson;
    }

    private static ClienteEntity toClienteEntity(Cliente data){
        return ClienteEntity.builder()
                .estado(data.isEstado())
                .contrasena(data.getContrasena())
                .build();
    }
}
