package br.com.meta.avaliacao.gestaocontato.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.meta.avaliacao.gestaocontato.service.UsuarioService;
import io.jsonwebtoken.Jwts;

/**
 * JWTAuthorizationFilter
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final UsuarioService usuarioService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UsuarioService customUserDetailService) {
        super(authenticationManager);
        this.usuarioService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null){ //|| !header.startsWith(SecurityEnum.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getPasswordAuthenticationToken(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);

    }
    
    private UsernamePasswordAuthenticationToken getPasswordAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null)
            return null;

        String username = Jwts.parser().setSigningKey("TheBeatles")
        .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody().getSubject();
        
        UserDetails userDetails = usuarioService.loadUserByUsername(username);
        
        return username != null ? new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()): null;
    }
    
}