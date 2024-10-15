package com.ozdeniz.fittrack.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class FittrackExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest webRequest) {
        String message = "Duplicate entry detected. Please use a different email."; // Kısa hata mesajı
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST; // İlgili HTTP durumu

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));
        problemDetail.setTitle(ex.getClass().getSimpleName());
        problemDetail.setStatus(httpStatus.value());
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());

        return new ResponseEntity<>(problemDetail, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode httpStatus, WebRequest webRequest) {
        var validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, validationErrors.toString());
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));
        problemDetail.setTitle(exception.getClass().getSimpleName());
        problemDetail.setStatus(httpStatus.value());
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());

        return new ResponseEntity<>(problemDetail, httpStatus);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(WebRequest webRequest, Exception exception) {
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus httpStatus = Objects.nonNull(responseStatus) ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String message = Objects.nonNull(exception.getLocalizedMessage()) ? exception.getLocalizedMessage() : httpStatus.getReasonPhrase();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));
        problemDetail.setTitle(exception.getClass().getSimpleName());
        problemDetail.setStatus(httpStatus.value());
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());

        return new ResponseEntity<>(problemDetail, httpStatus);
    }

    @ExceptionHandler(FittrackException.class)
    public ResponseEntity<Object> handleJobException(WebRequest webRequest, FittrackException fittrackException) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(fittrackException.getStatus(), fittrackException.getMessage());
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));
        problemDetail.setTitle(fittrackException.getClass().getSimpleName());
        problemDetail.setStatus(fittrackException.getStatus().value());
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return new ResponseEntity<>(problemDetail, fittrackException.getStatus());
    }

}
