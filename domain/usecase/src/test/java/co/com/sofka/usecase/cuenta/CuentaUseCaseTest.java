package co.com.sofka.usecase.cuenta;

import co.com.sofka.model.cliente.Cliente;
import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.cuenta.gateways.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CuentaUseCaseTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private  CuentaUseCase cuentaUseCase;

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

    @Test
    void obtenerCuentaTest(){
        Mockito.when(cuentaRepository.getCuenta(1))
                .thenReturn(Mono.just(cuenta));
        StepVerifier.create(cuentaUseCase.getCuenta(1))
                .assertNext(item->{
                    assertEquals(item.getNumeroCuenta(), cuenta.getNumeroCuenta());
                }).verifyComplete();
    }

    @Test
    void botenerCuentasTest(){
        Mockito.when(cuentaRepository.getAll())
                .thenReturn(Flux.just(cuenta, cuenta1));
        StepVerifier.create(cuentaUseCase.getAll(), 2)
                .expectNextCount(2)
                .verifyComplete();
    }

}