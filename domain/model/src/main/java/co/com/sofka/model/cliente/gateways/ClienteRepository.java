package co.com.sofka.model.cliente.gateways;

import co.com.sofka.model.cliente.Cliente;
import reactor.core.publisher.Mono;

public interface ClienteRepository {

    Mono<Cliente> saveCliente(Cliente cliente);
    Mono<Cliente> getCliente(String id);
    Mono<Cliente> updateCliente(Cliente cliente);
    Mono<Void> deleteCliente(String id);
}
