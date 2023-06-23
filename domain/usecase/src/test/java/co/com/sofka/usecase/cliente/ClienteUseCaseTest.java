package co.com.sofka.usecase.cliente;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cliente.gateways.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteUseCaseTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteUseCase clienteUseCase;

    Cliente cliente = Cliente.builder()
            .idCliente(1)
            .identificacion("1234566")
            .contrasena("contra")
            .telefono("221133")
            .edad(25)
            .estado(true)
            .genero("M")
            .direccion("cll 200 #50")
            .identificacion("112233")
            .nombre("jose perez")
            .build();

    @Test
    void guardarClienteTest(){
        Mockito.when(clienteRepository.saveCliente(cliente))
                .thenReturn(Mono.just(cliente));
        StepVerifier.create(clienteRepository.saveCliente(cliente))
                .assertNext(clli->{
                    assertEquals(cliente.getIdentificacion(), clli.getIdentificacion());
                }).verifyComplete();

    }

    @Test
    void obtenerClienteTest(){
        Mockito.when(clienteRepository.getCliente("1234566"))
                .thenReturn(Mono.just(cliente));
        StepVerifier.create(clienteRepository.getCliente("1234566"))
                .assertNext(clli->{
                    assertEquals(cliente.getIdentificacion(), clli.getIdentificacion());
                }).verifyComplete();

    }

    @Test
    void actualizarClienteTest(){
        Mockito.when(clienteRepository.updateCliente(cliente))
                .thenReturn(Mono.just(cliente));
        StepVerifier.create(clienteRepository.updateCliente(cliente))
                .assertNext(clli->{
                    assertEquals(cliente.getIdentificacion(), clli.getIdentificacion());
                }).verifyComplete();

    }

    @Test
    void deleteClienteTest(){
        Mockito.when(clienteRepository.deleteCliente(25))
                .thenReturn(Mono.empty());
        StepVerifier.create(clienteRepository.deleteCliente(25))
                .verifyComplete();

    }
}