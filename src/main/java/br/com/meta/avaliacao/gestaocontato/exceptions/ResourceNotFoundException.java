package br.com.meta.avaliacao.gestaocontato.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
 public class ResourceNotFoundException  extends RuntimeException{

    private static final long serialVersionUID = 2680684973812616552L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}