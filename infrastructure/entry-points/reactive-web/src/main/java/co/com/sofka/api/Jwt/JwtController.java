package co.com.sofka.api.Jwt;

import co.com.sofka.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class JwtController {

    @Autowired
    private final JwtGenerator jwtGenerator;

    @GetMapping
    public Mono<String> getToken(String user){
        return Mono.just(jwtGenerator.generateToken(user));
    }
}
