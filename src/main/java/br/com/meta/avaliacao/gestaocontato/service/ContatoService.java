package br.com.meta.avaliacao.gestaocontato.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.meta.avaliacao.gestaocontato.exceptions.ResourceNotFoundException;
import br.com.meta.avaliacao.gestaocontato.model.Contato;
import br.com.meta.avaliacao.gestaocontato.repository.ContatoRepository;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Service
public class ContatoService {

    private ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public List<Contato> findAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Contato> pagedResult = contatoRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        }

        return new ArrayList<Contato>();
    }

    public Page<Contato> findAll(Pageable pageable) {
        return contatoRepository.findAll(pageable);
    }

    public Optional<Contato> getById(String id) {
        return contatoRepository.findById(id);
    }

    public Contato insert(Contato contato) {
        return contatoRepository.save(contato);
    }

    public Contato update(Contato contato) {
        getContatoOrThrowsException(contato.getId());
        return contatoRepository.save(contato);
    }

    public void delete(String id) {
        getContatoOrThrowsException(id);
        contatoRepository.deleteById(id);
    }

    public Contato getContatoOrThrowsException(String id) {
        Optional<Contato> contato = getById(id);
        return contato.orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com ID: " + id));
    }
    
}