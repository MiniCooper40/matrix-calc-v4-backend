package com.matrixcalcv4backend.matrixcalcv4backend.exceptions;

import com.matrixcalcv4backend.matrixcalcv4backend.models.Matrix;
import lombok.Getter;

@Getter
public class InvalidMatrixException extends RuntimeException {

    public InvalidMatrixException(String message) {
        super(message);
    }

    private String operation;

    public InvalidMatrixException(String operation, Matrix A, Matrix B) {
        super(
                "Impossible to compute " + operation + " for A( rows = " +
                            A.getNumRows() + ", cols = " + A.getNumCols() + ") with B( rows = " +
                            B.getNumRows() + ", cols = " + B.getNumCols() + ")"
        );
        this.operation = operation;
    }
}
