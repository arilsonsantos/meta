package br.com.meta.avaliacao.gestaocontato.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Getter
@Setter
@ToString(of = "nome")
@EqualsAndHashCode(exclude = "grupos")
@Entity
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String nome;

    @ManyToMany
    @JoinTable(name = "usuario_grupo",
    joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_grupo", referencedColumnName = "id"))
    private Collection<Grupo> grupos;



    
}