package spring.config;

import io.jsonwebtoken.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import spring.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "secretkey";
    private final int TOKEN_VALIDITY_TIME = 10; // in min

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    private final JwtParser jwtParser;

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
    }

    public String createToken(User user) {
        // мы получаем логин пользователя
        Claims claims = Jwts.claims().setSubject(user.getLogin());
        //claims.put("authorities", user.getAuthorities());
        // берем текущую дату
        Date tokenCreateTime = new Date();
        // к этой дате прибавляем 10 минут
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(TOKEN_VALIDITY_TIME));
        // создаем токен
        return Jwts.builder()
                .setClaims(claims) // в него кладем логин
                .setExpiration(tokenValidity) // кладем дату, когда он перестанет быть валидным
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // подписываем его
                .compact();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return jwtParser.parseClaimsJws(token).getBody();
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }
}
