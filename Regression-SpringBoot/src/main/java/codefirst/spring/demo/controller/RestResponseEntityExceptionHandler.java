package codefirst.spring.demo.controller;

import codefirst.spring.demo.dto.ExceptionDTO;
import codefirst.spring.demo.exception.CodefirstException;
import codefirst.spring.demo.mapper.ExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    ExceptionMapper exceptionMapper;

    @ExceptionHandler
    ResponseEntity<Object> handleCodefirstException(CodefirstException ex, WebRequest request) {
        ExceptionDTO exceptionDTO = exceptionMapper.toExceptionDTO(ex);
        return handleExceptionInternal(ex, exceptionDTO, new HttpHeaders(), ex.getHttpStatus(), request);
    }
}
