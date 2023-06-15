package co.com.sofka.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;

public class JwtVerifier {
    private final JwtParser jwtParser;

    public JwtVerifier(JwtParser jwtParser) {
        this.jwtParser = jwtParser;
    }

    public String getUsernameFromToken(String token) {
        return jwtParser.parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
