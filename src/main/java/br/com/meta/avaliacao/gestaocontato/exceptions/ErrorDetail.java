package br.com.meta.avaliacao.gestaocontato.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Arilson Santos
 * @since 27/07/2019
 */
@Getter
@Setter
public class ErrorDetail {

    protected String title;
    protected int status;
    protected String detail;
    protected LocalDateTime timestamp;
    protected String developerMessage;

    public static final class Builder {
        private static final ErrorDetail errorDetail = new ErrorDetail();

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            errorDetail.title = title;
            return this;
        }

        public Builder status(int status) {
            errorDetail.status = status;
            return this;
        }

        public Builder detail(String detail) {
            errorDetail.detail = detail;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            errorDetail.timestamp = timestamp;
            errorDetail.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            errorDetail.developerMessage = developerMessage;
            return this;
        }

        public ErrorDetail build() {
            return errorDetail;
        }
    }

}