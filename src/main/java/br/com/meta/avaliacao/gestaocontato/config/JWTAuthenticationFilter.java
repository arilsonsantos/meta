package br.com.meta.avaliacao.gestaocontato.config;

import static br.com.meta.avaliacao.gestaocontato.enumerations.SecurityEnum.HEADER_STRING;
import static br.com.meta.avaliacao.gestaocontato.enumerations.SecurityEnum.SECRET;
import static br.com.meta.avaliacao.gestaocontato.enumerations.SecurityEnum.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.meta.avaliacao.gestaocontato.enumerations.SecurityEnum;
import br.com.meta.avaliacao.gestaocontato.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWTAuthenticationFilter
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, 
            FilterChain chain,                                    
            Authentication authResult) throws IOException, ServletException {
        
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
        
        String token = Jwts.builder().setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + SecurityEnum.EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        String bearerToken = TOKEN_PREFIX + token;
        response.getWriter().write(bearerToken);
        response.addHeader(HEADER_STRING, bearerToken);
    }

}