package br.com.meta.avaliacao.gestaocontato.model.dto;

import javax.validation.constraints.NotNull;

import br.com.meta.avaliacao.gestaocontato.model.Contato;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Getter
@Setter
public class ContatoUpdate {
   
    @NotNull(message = "{contato.nome.size}")
    private String nome;

    @NotNull
    private String canal;

    @NotNull
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