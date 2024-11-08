package by.malanka.academy.api.exception.handler;

import by.malanka.academy.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    ProblemDetail handleEntityNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ProblemDetail handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        String errMsg = buildValidationErrorMessage(ex.getDetailMessageArguments());
        log.error(errMsg, ex);
        return ResponseEntity.status(status).body(ProblemDetail.forStatusAndDetail(status, errMsg));
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(
            @NonNull HandlerMethodValidationException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        String errMsg = buildValidationErrorMessage(ex.getDetailMessageArguments());
        log.error(errMsg, ex);
        return ResponseEntity.status(status).body(ProblemDetail.forStatusAndDetail(status, errMsg));
    }

    private String buildValidationErrorMessage(Object[] detailMessageArguments) {
        return Optional.ofNullable(detailMessageArguments).stream()
                .flatMap(Arrays::stream)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
