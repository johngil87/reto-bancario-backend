package co.com.sofka.api.cuenta;

import co.com.sofka.model.cuenta.Cuenta;
import co.com.sofka.model.ex.BusinessExceptions;
import co.com.sofka.security.JwtVerifier;
import co.com.sofka.usecase.cuenta.CuentaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cuenta", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CuentaController {

    @Autowired
    private final JwtVerifier jwtVerifier;
    private final CuentaUseCase cuentaUseCase;

    @PostMapping
    public Mono<Cuenta> guardarCuenta(@RequestBody Cuenta cuenta, @RequestHeader("my-token") String token){
        if(!jwtVerifier.validateToken(token)){
            return Mono.error(BusinessExceptions.Type.INVALID_TOKEN.build());
        }
        return cuentaUseCase.saveCuenta(cuenta);
    }

    @GetMapping("/id")
    public  Mono<Cuenta> obtenerCuenta(@RequestParam Integer id){
        return cuentaUseCase.getCuenta(id);
    }

    @GetMapping
    public  Flux<Cuenta> obtenerCuentas(){
        return cuentaUseCase.getAll();
    }

    @GetMapping("/listId")
    public Flux<Cuenta> obtenerCuentas(@RequestParam List<Integer> ids){
        return cuentaUseCase.getAllCuentas(ids);
    }

    @DeleteMapping("/id")
    public Mono<Void> eliminarCuenta(@RequestParam Integer id, @RequestHeader("my-token") String token){
        if(!jwtVerifier.validateToken(token)){
            return Mono.error(BusinessExceptions.Type.INVALID_TOKEN.build());
        }
        return cuentaUseCase.deleteCuenta(id);
    }

    @PutMapping
    public Mono<Cuenta> actualizarCuenta(@RequestBody Cuenta cuenta, @RequestHeader("my-token") String token){
        if(!jwtVerifier.validateToken(token)){
            return Mono.error(BusinessExceptions.Type.INVALID_TOKEN.build());
        }
        return cuentaUseCase.updateCuenta(cuenta);
    }
}
