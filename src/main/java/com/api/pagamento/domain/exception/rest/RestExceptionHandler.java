package com.api.pagamento.domain.exception.rest;

import com.api.pagamento.domain.dto.request_response.response.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
            @NonNull HttpStatus status, @NonNull WebRequest request) {
        String fieldErrors = ex.getBindingResult().getFieldErrors().get(0).toString();
        String field = ex.getBindingResult().getFieldErrors().get(0).getField();
        String defaultMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        String message = "O campo " + field + defaultMessage;

        ResponseMessage error = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), fieldErrors, message);
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}

