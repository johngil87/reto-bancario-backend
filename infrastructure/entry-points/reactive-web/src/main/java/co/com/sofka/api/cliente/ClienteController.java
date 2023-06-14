package co.com.sofka.api.cliente;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.usecase.cliente.ClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    @GetMapping("/id")
    public Mono<Cliente> obtenerCliente(@RequestParam String id){
        return clienteUseCase.getClient(id);
    }

    @GetMapping
    public Flux<Cliente> obtenerClientes(){
        return clienteUseCase.getAll();
    }

    @PostMapping
    public  Mono<Cliente> guardarCliente(@RequestBody Cliente cliente){
        return clienteUseCase.saveClient(cliente);
    }

    @PutMapping
    public  Mono<Cliente> actualizarCliente(@RequestBody Cliente cliente){
        return clienteUseCase.updateClient(cliente);
    }

    @DeleteMapping
    public  Mono<Void> eliminarCliente(@RequestParam String id){
        return clienteUseCase.deleteClient(id);
    }
}
