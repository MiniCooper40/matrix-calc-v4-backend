package com.matrixcalcv4backend.matrixcalcv4backend.exceptions;

import org.apache.commons.lang3.math.Fraction;

public class InvalidVectorException extends Exception {

    public InvalidVectorException(String message) {
        super(message);
    }

    public InvalidVectorException(String operation, Fraction[] V, Fraction[] W) {
        super (
                "Impossible to compute " + operation +
                        "for V( n = " + V.length + ") " +
                        "and W( n = " + W.length + ")"
        );
    }
}
