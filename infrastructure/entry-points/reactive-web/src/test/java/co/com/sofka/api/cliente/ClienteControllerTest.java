package co.com.sofka.api.cliente;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.ex.BusinessExceptions;
import co.com.sofka.security.JwtVerifier;
import co.com.sofka.usecase.cliente.ClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= ClienteController.class)
@WebFluxTest(controllers = ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    JwtVerifier jwtVerifier;

    @MockBean
    private ClienteUseCase clienteUseCase;

    Cliente cliente = Cliente.builder()
            .idCliente(1)
            .contrasena("contra")
            .telefono("221133")
            .edad(25)
            .estado(true)
            .genero("M")
            .direccion("cll 200 #50")
            .identificacion("112233")
            .nombre("jose perez")
            .build();

    Cliente cliente2 = Cliente.builder()
            .idCliente(2)
            .contrasena("contra")
            .telefono("22113300")
            .edad(25)
            .estado(true)
            .genero("M")
            .direccion("cll 200 #50")
            .identificacion("112233")
            .nombre("jose perez")
            .build();

    @BeforeEach
    public void init(){
        Mockito.when(jwtVerifier.validateToken("euffddafaca345a")).thenReturn(true);
        Mockito.when(clienteUseCase.getClient(1)).thenReturn(Mono.just(cliente));
        Mockito.when(clienteUseCase.saveClient(cliente)).thenReturn(Mono.just(cliente));
        Mockito.when(clienteUseCase.updateClient(cliente)).thenReturn(Mono.just(cliente));
        Mockito.when(clienteUseCase.deleteClient(1)).thenReturn(Mono.empty());
        Mockito.when(clienteUseCase.getClient(0)).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()));
    }

    @Test
    void guardarClienteTest(){
        webTestClient.post()
                .uri("/api/cliente")
                .header("my-token", "euffddafaca345a")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(cliente), Cliente.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.identificacion").isEqualTo("112233");
    }

    @Test
    void actualizarClientetest(){
        webTestClient.put()
                .uri("/api/cliente")
                .header("my-token", "euffddafaca345a")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(cliente), Cliente.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.identificacion").isEqualTo("112233");
    }

    /*@Test
    void actualizarClienteErrorTest(){
        Mockito.when(clienteUseCase.updateClient(cliente2)).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()));
        webTestClient.put()
                .uri("/api/cliente")
                .header("my-token", "euffddafaca345a")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(cliente2), Cliente.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }*/

    @Test
    void obtenerClienteTest(){
        webTestClient.get()
                .uri("/api/cliente?id=0")
                .exchange()
                .expectStatus().isOk();
    }

}