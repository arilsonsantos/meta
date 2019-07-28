package br.com.meta.avaliacao.gestaocontato.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.meta.avaliacao.gestaocontato.model.Usuario;
import br.com.meta.avaliacao.gestaocontato.repository.UsuarioRepository;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private String strRoles;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsernameFetchRole(username)
            .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));
        
        strRoles = "";
        List<String> nomesGrupos = usuario.getGrupos().stream().map(g -> g.getNome()).collect(Collectors.toList());
        nomesGrupos.forEach(ng -> strRoles += "ROLE_" + ng + ",");
        List<GrantedAuthority> grupos = AuthorityUtils.commaSeparatedStringToAuthorityList(strRoles);

        return new User(usuario.getUsername(), usuario.getPassword(), grupos);
	}

    
}