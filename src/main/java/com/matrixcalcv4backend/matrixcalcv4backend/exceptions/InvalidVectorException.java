package com.matrixcalcv4backend.matrixcalcv4backend.exceptions;

import lombok.Getter;
import org.apache.commons.lang3.math.Fraction;

@Getter
public class InvalidVectorException extends RuntimeException {

    private String operation;
    public InvalidVectorException(String message) {
        super(message);
    }

    public InvalidVectorException(String operation, Fraction[] V, Fraction[] W) {
        super (
                "Impossible to compute " + operation +
                        "for V( n = " + V.length + ") " +
                        "and W( n = " + W.length + ")"
        );
        this.operation = operation;
    }
}
