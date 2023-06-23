package co.com.sofka.security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    //@Value("${jwt.secret}")
    private String secret =  "john122df2e43d9f543e162f92f647cb462cfd2c2d5da61d2290a6745e791d7b4b04542df2e43d9f543e162f92f647cb462cfd2c2d5da61d2";

    //@Value("${jwt.expiration}")
    private Long expiration=600000L;

    @Bean
    public JwtParser jwtParser() {
        return Jwts.parserBuilder().setSigningKey(secret).build();
    }

    @Bean
    public JwtGenerator jwtGenerator() {
        return new JwtGenerator(secret, expiration);
    }

    @Bean
    public JwtVerifier jwtVerifier() {
        return new JwtVerifier(jwtParser());
    }
}
