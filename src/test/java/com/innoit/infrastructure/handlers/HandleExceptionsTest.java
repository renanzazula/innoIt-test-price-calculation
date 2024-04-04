package com.innoit.infrastructure.handlers;

import com.innoit.domain.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HandleExceptionsTest {

    @Test
    void testHandleBadRequestException() {
        HandleExceptions handleExceptions = new HandleExceptions();
        BadRequestException badRequestException = new BadRequestException("Bad Request");
        ResponseEntity<ErrorResponse> responseEntity = handleExceptions.handleBadRequestException(badRequestException);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Bad Request", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleInternalServerErrorException() {
        HandleExceptions handleExceptions = new HandleExceptions();
        Exception internalServerException = new Exception("Internal Server Error");
        ResponseEntity<ErrorResponse> responseEntity = handleExceptions.handleInternalServerErrorException(internalServerException);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Internal Server Error", responseEntity.getBody().getMessage());
    }


}
