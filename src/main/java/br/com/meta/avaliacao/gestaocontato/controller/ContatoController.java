package br.com.meta.avaliacao.gestaocontato.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meta.avaliacao.gestaocontato.model.Contato;
import br.com.meta.avaliacao.gestaocontato.model.dto.ContatoCreate;
import br.com.meta.avaliacao.gestaocontato.model.dto.ContatoUpdate;
import br.com.meta.avaliacao.gestaocontato.service.ContatoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@RestController
@RequestMapping("/v1")
@ApiResponses(value = { 
    @ApiResponse(code = 401, message = ""),
    @ApiResponse(code = 404, message = "") })
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping(path = "user/contatos")
    @ApiOperation(value = "Retorna uma lista de contatos", response = Contato[].class)
    @ApiResponses(value = @ApiResponse(code = 200, message = ""))
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
                            
        List<Contato> contatos = contatoService.findAll(page, size);
        return new ResponseEntity<>(contatos, HttpStatus.OK);
    }

    @GetMapping(path = "user/contatos/{id}")
    @ApiResponses(value = @ApiResponse(code = 200, message = ""))
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        Contato contato = contatoService.getContatoOrThrowsException(id);
        return new ResponseEntity<>(contato, HttpStatus.OK);
    }

    @PostMapping(path = "admin/contatos")
    @Transactional(rollbackFor = Exception.class)
    @ApiResponses(value = @ApiResponse(code = 201, message = ""))
    public ResponseEntity<?> create(@Valid @RequestBody ContatoCreate contatoCreate) {
        Contato contato = contatoCreate.criaContato();
        contatoService.insert(contato);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "admin/contatos/{id}")
    @Transactional(rollbackFor = Exception.class)
    @ApiResponses(value = @ApiResponse(code = 204, message = ""))
    public ResponseEntity<?> update(@Valid @RequestBody ContatoUpdate contatoUpdate, @PathVariable("id") String id) {
        Contato contato = contatoUpdate.criaContato();
        contato.setId(id);
        contatoService.update(contato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "admin/contatos/{id}")
    @ApiResponses(value = @ApiResponse(code = 204, message = ""))
    public ResponseEntity<?> delete(@PathVariable String id) {
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}