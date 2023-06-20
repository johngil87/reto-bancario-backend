package co.com.sofka.jpa.persona;

import co.com.sofka.jpa.entities.ClienteEntity;
import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.jpa.mapper.ClienteMapper;
import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cliente.gateways.ClienteRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PersonaAdapter extends AdapterOperations<Cliente, ClienteEntity,Integer, ClienteJpaRepository> implements ClienteRepository {

    public PersonaAdapter(ClienteJpaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d-> mapper.mapBuilder(d,Cliente.ClienteBuilder.class).build());
    }

    @Override
    public Mono<Cliente> saveCliente(Cliente cliente) {
        ClienteEntity newCliente = ClienteMapper.toEntity(cliente);
        return Mono.just(ClienteMapper.toData(repository.save(newCliente)));
    }

    @Override
    public Mono<Cliente> getCliente(Integer id) {
        return repository.findById(id).map(item -> Mono.just(ClienteMapper.toData(item))).orElse(Mono.empty());
    }

    @Override
    public Mono<Cliente> updateCliente(Cliente cliente) {
        return Mono.just(ClienteMapper.toData(repository.save(ClienteMapper.toEntity(cliente))));
    }

    @Override
    public Flux<Cliente> getAll() {
        return Flux.fromIterable(ClienteMapper.toDataList(repository.findAll()));
    }

    @Override
    public Mono<Void> deleteCliente(Integer id) {
        repository.deleteById(id);
        return Mono.empty();
    }

    @Override
    public Mono<Cliente> getCliente(String id) {
        return Flux.fromIterable(ClienteMapper.toDataList(repository.getByIdentificacion(id)))
                .next();
    }
}
