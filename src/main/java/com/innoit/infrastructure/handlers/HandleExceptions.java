package com.innoit.infrastructure.handlers;

import com.innoit.domain.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleExceptions {
    public static final String NOT_FOUND = "Not found";
    @ExceptionHandler({
            BadRequestException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex) {
        return new ResponseEntity<>(getErrorResponse(ex, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(Exception ex) {
        return new ResponseEntity<>(getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse getErrorResponse(Exception ex, HttpStatus httpStatus) {
        String causeMessage = ex.getMessage();
        int httpCode = httpStatus.value();
        String httpName = httpStatus.name();
        String clazz = getClassName(ex);
        String method = ex.getStackTrace().length > 0 ? ex.getStackTrace()[0].getMethodName() : NOT_FOUND;
        int line = ex.getStackTrace().length > 0 ? ex.getStackTrace()[0].getLineNumber() : 0;

        return ErrorResponse.builder()
                .clazz(clazz)
                .httpStatus(httpCode)
                .message(causeMessage)
                .method(method)
                .type(httpName)
                .line(line)
                .build();
    }

    private String getClassName(Exception ex) {
        String className = NOT_FOUND;
        try {
            className = Class.forName(ex.getStackTrace()[0].getClassName()).getName();
        } catch (Exception ignored) {
            //  ignored this catch
        }
        return className;
    }

}
