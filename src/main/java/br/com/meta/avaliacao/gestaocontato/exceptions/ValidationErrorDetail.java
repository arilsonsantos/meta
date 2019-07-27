package br.com.meta.avaliacao.gestaocontato.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Getter
@Setter
public class ValidationErrorDetail extends ErrorDetail {

    private Map<String, String> errors;

    public static final class Builder {
        private static final ValidationErrorDetail objBuilder = new ValidationErrorDetail();

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            objBuilder.title = title;
            return this;
        }

        public Builder status(int status) {
            objBuilder.status = status;
            return this;
        }

        public Builder detail(String detail) {
            objBuilder.detail = detail;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            objBuilder.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            objBuilder.developerMessage = developerMessage;
            return this;
        }


        public Builder addError(Map<String, String> fieldsErrors) {
            objBuilder.errors = fieldsErrors;
            return this;
        }

        public ValidationErrorDetail build() {
            return objBuilder;
        }

    }

}