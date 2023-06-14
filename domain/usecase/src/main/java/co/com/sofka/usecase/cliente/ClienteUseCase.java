package co.com.sofka.usecase.cliente;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cliente.gateways.ClienteRepository;
import co.com.sofka.model.ex.BusinessExceptions;
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
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()));
    }

    public Mono<Cliente> updateClient(Cliente cliente){
        return getClient(cliente.getIdentificacion())
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()))
                .flatMap(item-> clienteRepository.updateCliente(cliente));
    }

    public Mono<Void> deleteClient(String id){
        return getClient(id)
                .switchIfEmpty(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()))
                .flatMap(item-> clienteRepository.deleteCliente(id));
    }
}
