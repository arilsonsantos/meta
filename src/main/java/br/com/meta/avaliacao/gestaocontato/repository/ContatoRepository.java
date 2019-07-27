package br.com.meta.avaliacao.gestaocontato.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.meta.avaliacao.gestaocontato.model.Contato;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Repository
public interface ContatoRepository extends PagingAndSortingRepository<Contato, String>{
    
}