package br.com.meta.avaliacao.gestaocontato.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * RestExceptionHandler
 */
@ControllerAdvice
public final class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fe : fieldErros) {
            errors.put(fe.getField(), fe.getDefaultMessage());
        }

        ValidationErrorDetail details = ValidationErrorDetail.Builder
        .newBuilder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .title("Argumento inválido")
        .detail("Argumento inválido nos campos")
        .addError(errors).developerMessage(exception.getClass().getName())
        .build();

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetail details = ErrorDetail.Builder.newBuilder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title("Erro Interno")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();

        return new ResponseEntity<>(details, status);
    }

}