package co.com.sofka.model.cliente.gateways;

import co.com.sofka.model.cliente.Cliente;
import reactor.core.publisher.Mono;

public interface ClienteRepository {

    Mono<Cliente> savePersona(Cliente cliente);
}
