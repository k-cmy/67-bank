package com.sixseven.sixsevenBank.exceptions;

import com.sixseven.sixsevenBank.res.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // whenever this going to keep an eye on all the controller we have
// whenever an error is thrown within any request that comes through the controller , those will be handle by this class

public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response<?>> handleAllUnknownExceptions(Exception ex ) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Response<?>> handleNotFoundExceptions(Exception ex ) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    public ResponseEntity<Response<?>> handleInsufficientBalance(Exception ex ) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(value = InvalidTransactionException.class)
    public ResponseEntity<Response<?>> handleInvalidTransaction(Exception ex ) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Response<?>> handleBadRequestException(Exception ex) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
