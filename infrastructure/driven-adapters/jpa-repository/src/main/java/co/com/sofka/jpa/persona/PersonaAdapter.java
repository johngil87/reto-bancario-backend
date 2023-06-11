package co.com.sofka.jpa.persona;

import co.com.sofka.jpa.entities.PersonaEntity;
import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.jpa.mapper.PersonaMapper;
import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cliente.gateways.ClienteRepository;
import co.com.sofka.model.persona.Persona;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class PersonaAdapter extends AdapterOperations<Persona, PersonaEntity,String, PersonaJpaRepository> implements ClienteRepository {

    protected PersonaAdapter(PersonaJpaRepository repository, ObjectMapper mapper, Function<PersonaEntity, Persona> toEntityFn) {
        super(repository, mapper, toEntityFn);
    }

    @Override
    public Mono<Cliente> savePersona(Cliente cliente) {
        return Mono.just(PersonaMapper.toData(repository.save(PersonaMapper.toNewEntity(cliente))));
    }
}
