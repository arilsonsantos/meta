package br.com.meta.avaliacao.gestaocontato.util;

import static br.com.meta.avaliacao.gestaocontato.enumerations.SecurityEnum.SECRET;
import static br.com.meta.avaliacao.gestaocontato.enumerations.SecurityEnum.TOKEN_PREFIX;

import java.util.Date;

import br.com.meta.avaliacao.gestaocontato.enumerations.SecurityEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * TokenUtil
 */
public class TokenUtil {

    public  String getToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityEnum.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        String bearerToken = TOKEN_PREFIX + token;

        return bearerToken;
    }
}