package br.com.meta.avaliacao.gestaocontato.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.meta.avaliacao.gestaocontato.model.Usuario;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername(@Param("username") String username);
    
    @Query("select u from Usuario u join fetch u.grupos where u.username = :username")
    Optional<Usuario> findByUsernameFetchRole(@Param("username") String username);
    
}