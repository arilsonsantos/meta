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
@ApiResponses(value =  @ApiResponse(code = 401, message = "Unauthorized"))
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }
    
    @ApiResponses(value = @ApiResponse(code = 200, message = ""))
    @ApiOperation(value = "Retorna uma lista de objetos do tipo Contato")
    @GetMapping(path = "user/contatos", produces = "application/json")
    public ResponseEntity<List<Contato>> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        page = page >= 1 ? page - 1 : 0;
                            
        List<Contato> contatos = contatoService.findAll(page, size);
        return new ResponseEntity<>(contatos, HttpStatus.OK);
    }

    @ApiResponses(value = @ApiResponse(code = 200, message = ""))
    @ApiOperation(value = "Retorna um único objeto do tipo Contato")
    @GetMapping(path = "user/contatos/{id}", produces = "application/json")
    public ResponseEntity<Contato> getById(@PathVariable("id") String id) {
        Contato contato = contatoService.getContatoOrThrowsException(id);
        return new ResponseEntity<>(contato, HttpStatus.OK);
    }

    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Updated"),
        @ApiResponse(code = 400, message = "Bad Request")})
    @ApiOperation(value = "Salva um registro do tipo Contato")
    @PostMapping(path = "admin/contatos", produces = "application/json")
    public ResponseEntity<?> create(@Valid @RequestBody ContatoCreate contatoCreate) {
        Contato contato = contatoCreate.criaContato();
        contatoService.insert(contato);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 404, message = "Not Found")})
    @ApiOperation(value = "Atualiza um registro do tipo Contato")
    @PutMapping(path = "admin/contatos/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> update(@Valid @RequestBody ContatoUpdate contatoUpdate, @PathVariable("id") String id) {
        Contato contato = contatoUpdate.criaContato();
        contato.setId(id);
        contatoService.update(contato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 404, message = "Not Found")})
    @ApiOperation(value = "Deleta um contato")
    @DeleteMapping(path = "admin/contatos/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}