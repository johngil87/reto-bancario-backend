package co.com.sofka.api.cuenta;

import co.com.sofka.api.cliente.ClienteController;
import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.ex.BusinessExceptions;
import co.com.sofka.usecase.cliente.ClienteUseCase;
import co.com.sofka.usecase.cuenta.CuentaUseCase;
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
@ContextConfiguration(classes= CuentaController.class)
@WebFluxTest(controllers = CuentaController.class)
class CuentaControllerTest {

    @Autowired
    WebTestClient testClient;

    @MockBean
    CuentaUseCase cuentaUseCase;

    @MockBean
    ClienteUseCase clienteUseCase;

    Cliente cliente = Cliente.builder()
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
            .contrasena("contra")
            .telefono("22113300")
            .edad(25)
            .estado(true)
            .genero("M")
            .direccion("cll 200 #50")
            .identificacion("000")
            .nombre("jose perez")
            .build();

    Cuenta cuenta = Cuenta.builder()
            .cliente(cliente)
            .saldoDisponible(2000000.00)
            .estado(true)
            .tipoCuenta("corriente")
            .saldoInicial(2000000.00)
            .numeroCuenta(1)
            .build();

    Cuenta cuenta1 = Cuenta.builder()
            .cliente(cliente2)
            .saldoDisponible(2000000.00)
            .estado(true)
            .tipoCuenta("corriente")
            .saldoInicial(2000000.00)
            .numeroCuenta(2)
            .build();

    @BeforeEach
    void init(){
        Mockito.when(clienteUseCase.getClient("221133")).thenReturn(Mono.just(cliente));
        Mockito.when(cuentaUseCase.saveCuenta(cuenta)).thenReturn(Mono.just(cuenta));
        Mockito.when(clienteUseCase.getClient("000")).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()));
        Mockito.when(cuentaUseCase.saveCuenta(cuenta1)).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()));
        Mockito.when(cuentaUseCase.getCuenta(1)).thenReturn(Mono.just(cuenta));
        Mockito.when(cuentaUseCase.updateCuenta(cuenta1)).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_CLIENT.build()));
        Mockito.when(cuentaUseCase.updateCuenta(cuenta)).thenReturn(Mono.just(cuenta));

    }

    @Test
    void guardarCuenteTest(){
        testClient.post()
                .uri("/api/cuenta")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(cuenta),Cuenta.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.tipoCuenta").isEqualTo("corriente")
                .jsonPath("$.numeroCuenta").isEqualTo(1);
    }

    @Test
    void guardarCuentaErrorTest(){
        testClient.post()
                .uri("/api/cuenta")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(cuenta1),Cuenta.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void obtenerCuentaTest(){
        testClient.get()
                .uri("/api/cuenta/id?id=1")
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void actualizarCuentaErrorTest(){
        testClient.put()
                .uri("/api/cuenta")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(cuenta1),Cuenta.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void actualizarCuentaTest(){
        testClient.put()
                .uri("/api/cuenta")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(cuenta),Cuenta.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.tipoCuenta").isEqualTo("corriente")
                .jsonPath("$.numeroCuenta").isEqualTo(1);
    }
}