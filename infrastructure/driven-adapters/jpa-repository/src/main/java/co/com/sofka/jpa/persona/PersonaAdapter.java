package co.com.sofka.jpa.persona;

import co.com.sofka.jpa.entities.ClienteEntity;
import co.com.sofka.jpa.entities.PersonaEntity;
import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.jpa.mapper.PersonaMapper;
import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cliente.gateways.ClienteRepository;
import co.com.sofka.model.persona.Persona;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Repository
public class PersonaAdapter extends AdapterOperations<Persona, PersonaEntity,String, PersonaJpaRepository> implements ClienteRepository {

    public PersonaAdapter(PersonaJpaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d-> mapper.mapBuilder(d,Persona.PersonaBuilder.class).build());
    }

    @Override
    public Mono<Cliente> saveCliente(Cliente cliente) {
        PersonaEntity newCliente = PersonaMapper.toEntity(cliente);
        return Mono.just(PersonaMapper.toData(repository.save(newCliente)));
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
