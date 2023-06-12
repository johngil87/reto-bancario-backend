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
    public Mono<Cliente> saveCliente(Cliente cliente) {
        return Mono.just(PersonaMapper.toData(repository.save(PersonaMapper.toEntity(cliente))));
    }

    @Override
    public Mono<Cliente> getCliente(String id) {
        return repository.findById(id).map(item -> Mono.just(PersonaMapper.toData(item))).orElse(Mono.empty());
    }

    @Override
    public Mono<Cliente> updateCliente(Cliente cliente) {
        return Mono.just(PersonaMapper.toData(repository.save(PersonaMapper.toEntity(cliente))));
    }

    @Override
    public Mono<Void> deleteCliente(String id) {
        repository.deleteById(id);
        return Mono.empty();
    }
}
