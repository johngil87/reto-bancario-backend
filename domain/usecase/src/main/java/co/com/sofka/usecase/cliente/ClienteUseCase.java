package co.com.sofka.usecase.cliente;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cliente.gateways.ClienteRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ClienteUseCase {

    private final ClienteRepository clienteRepository;

    public Mono<Cliente> saveClient(Cliente cliente){
        return clienteRepository.saveCliente(cliente);
    }

    public Mono<Cliente> getClient(String id){
        return clienteRepository.getCliente(id)
                .switchIfEmpty(Mono.error(new Exception("no existe cliente")));
    }

    public Mono<Cliente> updateClient(Cliente cliente){
        return clienteRepository.updateCliente(cliente);
    }

    public Mono<Void> deleteClient(String id){
        return clienteRepository.deleteCliente(id);    }
}
