package co.com.sofka.api.cliente;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.ex.BusinessExceptions;
import co.com.sofka.security.JwtGenerator;
import co.com.sofka.security.JwtVerifier;
import co.com.sofka.usecase.cliente.ClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClienteController {

    @Autowired
    private final JwtVerifier jwtVerifier;
    private final ClienteUseCase clienteUseCase;

    @GetMapping("/id")
    public Mono<Cliente> obtenerCliente(@RequestParam Integer id){
            return clienteUseCase.getClient(id);
    }

    @GetMapping
    public Flux<Cliente> obtenerClientes(){
        return clienteUseCase.getAll();
    }

    @PostMapping
    public  Mono<Cliente> guardarCliente(@RequestBody Cliente cliente, @RequestHeader("my-token") String token){
        if(!jwtVerifier.validateToken(token)){
            return Mono.error(BusinessExceptions.Type.INVALID_TOKEN.build());
        }
        return clienteUseCase.saveClient(cliente);
    }

    @PutMapping
    public  Mono<Cliente> actualizarCliente(@RequestBody Cliente cliente, @RequestHeader("my-token") String token){
        if(!jwtVerifier.validateToken(token)){
            return Mono.error(BusinessExceptions.Type.INVALID_TOKEN.build());
        }
        return clienteUseCase.updateClient(cliente);
    }

    @DeleteMapping
    public  Mono<Void> eliminarCliente(@RequestParam Integer id, @RequestHeader("my-token") String token){
        if(!jwtVerifier.validateToken(token)){
            return Mono.error(BusinessExceptions.Type.INVALID_TOKEN.build());
        }
        return clienteUseCase.deleteClient(id);
    }
}
