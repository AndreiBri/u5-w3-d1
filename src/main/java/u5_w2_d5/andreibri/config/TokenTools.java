package u5_w2_d5.andreibri.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import u5_w2_d5.andreibri.entities.Dipendente;
import u5_w2_d5.andreibri.exception.UnauthorizedException;

import java.util.Date;

@Component
public class TokenTools {

    private final String secret;

    public TokenTools(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(Dipendente user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Effettua di nuovo il login!");
        }

    }
}