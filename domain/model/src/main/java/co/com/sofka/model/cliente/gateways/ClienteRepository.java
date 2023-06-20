package co.com.sofka.model.cliente.gateways;

import co.com.sofka.model.cliente.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteRepository {

    Mono<Cliente> saveCliente(Cliente cliente);
    Mono<Cliente> getCliente(Integer id);
    Mono<Cliente> updateCliente(Cliente cliente);
    Flux<Cliente> getAll();
    Mono<Void> deleteCliente(Integer id);
    Mono<Cliente> getCliente(String id);
}
