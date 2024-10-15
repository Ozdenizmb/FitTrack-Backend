package com.ozdeniz.fittrack.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.Serializable;

import static com.ozdeniz.fittrack.exception.ErrorMessages.DEFAULT_ERROR_MESSAGE;

@Getter
@Setter
public class FittrackException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String detail;
    @NotNull
    private final HttpStatus status;

    protected FittrackException(HttpStatus status, Throwable throwable) {
        super(status.name(), throwable);
        this.status = status;
        this.message = throwable.getMessage();
        this.detail = !StringUtils.hasText(throwable.getMessage()) ? throwable.getMessage() : DEFAULT_ERROR_MESSAGE;
    }

    protected FittrackException(HttpStatus status, String message) {
        super(status.name());
        this.status = status;
        this.message = message;
        this.detail = null;
    }

    protected FittrackException(HttpStatus status, String message, String errorDetail) {
        super(status.name());
        this.status = status;
        this.message = message;
        this.detail = errorDetail;
    }

    public static FittrackException withStatusAndThrowable(HttpStatus status, Throwable throwable){
        return new FittrackException(status, throwable);
    }

    public static FittrackException withStatusAndMessage(HttpStatus status, String message){
        return new FittrackException(status, message);
    }

    public static FittrackException withStatusAndDetails(HttpStatus status, String message, String errorDetail){
        return new FittrackException(status, message, errorDetail);
    }
}
