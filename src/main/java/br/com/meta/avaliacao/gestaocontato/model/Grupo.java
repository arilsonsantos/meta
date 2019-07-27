package br.com.meta.avaliacao.gestaocontato.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Entity
@Data
@ToString(of = "nome")
@EqualsAndHashCode(exclude = { "usuarios" })
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "grupos")
    private Collection<Usuario> usuarios;

    
}