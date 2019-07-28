package br.com.meta.avaliacao.gestaocontato.model.dto;

import javax.validation.constraints.NotBlank;

import br.com.meta.avaliacao.gestaocontato.model.Contato;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Getter
@Setter
@ApiModel(description = "DTO para inserção de um novo contato")
public class ContatoCreate {
    
    @NotBlank(message = "{contato.nome.size}")
    private String nome;

    @NotBlank
    private String canal;

    @NotBlank
    private String valor;

    private String obs;

    public Contato criaContato() {
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setCanal(canal);
        contato.setValor(valor);
        contato.setObs(obs);

        return contato;
    }
    
}