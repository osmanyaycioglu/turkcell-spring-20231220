package training.spring.turkcellspring.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import training.spring.turkcellspring.jpa.UserObj;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JWTService {
    private Key key;

    public JWTService() {
        key = Keys.hmacShaKeyFor("sfjhskjhfksjdnkjcnksdjncksdjnckjsncdksjnckjsdcn".getBytes());
    }

    public String generate(UserObj userObjParam) {
        return Jwts.builder()
            .subject(userObjParam.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60 * 60)))
            .claim("xyz",
                   "abc")
            .signWith(key)
            .compact();

    }

    public Jws<Claims> validate(String token){
        JwtParser jwtParserLoc = Jwts.parser()
                                 .setSigningKey(key)
                                 .build();
        try {
            return jwtParserLoc.parseSignedClaims(token);
        } catch (JwtException eParam) {
            eParam.printStackTrace();
            return null;
        } catch (Exception eParam) {
            return null;
        }

    }

    public static void main(String[] args) {
        JWTService jwtServiceLoc = new JWTService();
        UserObj userObjLoc = new UserObj();
        userObjLoc.setUsername("osmanyay");
        String generateLoc = jwtServiceLoc.generate(userObjLoc);
        System.out.println(generateLoc);

        //generateLoc += "a";

        Jws<Claims> validateLoc = jwtServiceLoc.validate(generateLoc);
        System.out.println(validateLoc);

    }
}
