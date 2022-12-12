package com.matrixcalcv4backend.matrixcalcv4backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @Getter
    @AllArgsConstructor
    private static class ExceptionResponse {
        private String reason;
        private String suggestion;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidMatrixException.class)
    public ResponseEntity<ExceptionResponse> exception(InvalidMatrixException exception) {
        ExceptionResponse response = new ExceptionResponse(
                "Invalid input(s) for " + exception.getOperation(),
                "Double check that your input(s) have correct dimensions");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidVectorException.class)
    public ResponseEntity<ExceptionResponse> exception(InvalidVectorException exception) {
        ExceptionResponse response = new ExceptionResponse(
                "Invalid vector operation within input(s) " + exception.getOperation(),
                "Double check that your input(s) have correct dimensions");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = OutOfMatrixException.class)
    public ResponseEntity<ExceptionResponse> exception(OutOfMatrixException exception) {
        ExceptionResponse response = new ExceptionResponse(
                "Something went wrong",
                "Double check that your input(s) have correct dimensions");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
