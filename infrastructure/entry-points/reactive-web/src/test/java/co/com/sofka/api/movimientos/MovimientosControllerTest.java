package co.com.sofka.api.movimientos;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.ex.BusinessExceptions;
import co.com.sofka.model.movimientos.Movimientos;
import co.com.sofka.model.movimientoscliente.MovimientosCliente;
import co.com.sofka.security.JwtVerifier;
import co.com.sofka.usecase.cliente.ClienteUseCase;
import co.com.sofka.usecase.cuenta.CuentaUseCase;
import co.com.sofka.usecase.movimiento.MovimientoUseCase;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= MovimientosController.class)
@WebFluxTest(controllers = MovimientosController.class)
class MovimientosControllerTest {

    @Autowired
    WebTestClient testClient;

    @MockBean
    JwtVerifier jwtVerifier;

    @MockBean
    MovimientoUseCase movimientoUseCase;

    @MockBean
    CuentaUseCase cuentaUseCase;

    @MockBean
    ClienteUseCase clienteUseCase;

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
            .identificacion("000")
            .nombre("jose perez")
            .build();

    Cuenta cuenta = Cuenta.builder()
            .cliente(cliente)
            .saldoDisponible(20000.00)
            .estado(true)
            .tipoCuenta("corriente")
            .saldoInicial(20000.00)
            .numeroCuenta(1)
            .build();

    Cuenta cuenta1 = Cuenta.builder()
            .cliente(cliente2)
            .saldoDisponible(20000.00)
            .estado(true)
            .tipoCuenta("corriente")
            .saldoInicial(20000.00)
            .numeroCuenta(2)
            .build();

    Movimientos movimiento1 = Movimientos.builder()
            .tipo("debito")
            .valor(1000.0)
            .idCuenta(1)
            .build();

    Movimientos movimiento2 = Movimientos.builder()
            .tipo("debito")
            .valor(1000.0)
            .idCuenta(0)
            .build();

    Movimientos movimiento3 = Movimientos.builder()
            .tipo("debito")
            .valor(21000.0)
            .idCuenta(1)
            .build();

    Movimientos movimientos1 = Movimientos.builder()
            .tipo("debito")
            .saldo(10000.0)
            .identificacion("221133")
            .idMovimiento(1)
            .valor(1000.0)
            .fecha(new Date())
            .idCuenta(1)
            .build();

    Movimientos movimientos2 = Movimientos.builder()
            .tipo("debito")
            .saldo(10000.0)
            .identificacion("221133")
            .idMovimiento(1)
            .valor(1000.0)
            .fecha(new Date())
            .idCuenta(0)
            .build();

    MovimientosCliente movimientosCliente = MovimientosCliente.builder()
            .estado(true)
            .nombre("jose")
            .numeroCuenta(1)
            .saldo(20000.0)
            .build();


    @BeforeEach
    void init(){
        Mockito.when(jwtVerifier.validateToken("euffddafaca345a")).thenReturn(true);
        Mockito.when(cuentaUseCase.getCuenta(1)).thenReturn(Mono.just(cuenta));
        Mockito.when(cuentaUseCase.updateCuenta(cuenta)).thenReturn(Mono.just(cuenta));
        Mockito.when(movimientoUseCase.saveMovimiento(movimiento1)).thenReturn(Mono.just(movimientos1));
        Mockito.when(movimientoUseCase.updateMovimiento(movimientos1)).thenReturn(Mono.just(movimientos1));
        Mockito.when(movimientoUseCase.updateMovimiento(movimientos2)).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_ACOUNT.build()));
        Mockito.when(movimientoUseCase.saveMovimiento(movimiento2)).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_ACOUNT.build()));
        Mockito.when(cuentaUseCase.getCuenta(0)).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_ACOUNT.build()));
        Mockito.when(movimientoUseCase.saveMovimiento(movimiento3)).thenReturn(Mono.error(BusinessExceptions.Type.INVALID_ID_BALANCE.build()));
        Mockito.when(movimientoUseCase.deleteMovimiento(1)).thenReturn(Mono.empty());
        Mockito.when(clienteUseCase.getClient(1)).thenReturn(Mono.just(cliente));
        Mockito.when(movimientoUseCase.getListMovimientosCliente("1",new Date("2020/06/10"), new Date())).thenReturn(Flux.just(movimientosCliente));
    }

    @Test
    void guardarMovimientoTest(){
        testClient.post()
                .uri("/api/movimientos")
                .header("my-token", "euffddafaca345a")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(movimiento1), Movimientos.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void guardarMovimientoErrorTest(){
        testClient.post()
                .uri("/api/movimientos")
                .header("my-token", "euffddafaca345a")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(movimiento2), Movimientos.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void guardarMovimientoErrorTest2(){
        testClient.post()
                .uri("/api/movimientos")
                .header("my-token", "euffddafaca345a")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(movimiento3), Movimientos.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void uptadeMovimientoTest(){
        testClient.put()
                .uri("/api/movimientos")
                .header("my-token", "euffddafaca345a")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(movimientos1), Movimientos.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void uptadeMovimientoErrorTest(){
        testClient.put()
                .uri("/api/movimientos")
                .header("my-token", "euffddafaca345a")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(movimientos2), Movimientos.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void eliminarMovimientoErrorTest(){
        testClient.delete()
                .uri("/api/movimientos?id=0")
                .header("my-token", "euffddafaca345a")
                .exchange().expectStatus().is5xxServerError();
    }
}