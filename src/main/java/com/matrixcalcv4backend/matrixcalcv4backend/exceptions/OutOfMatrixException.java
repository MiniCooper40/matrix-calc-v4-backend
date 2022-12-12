package com.matrixcalcv4backend.matrixcalcv4backend.exceptions;

import com.matrixcalcv4backend.matrixcalcv4backend.models.Matrix;

public class OutOfMatrixException extends Exception {

    public enum OOMType {
        ROW, COL, BOTH
    }

    public OutOfMatrixException(String message) {
        super(message);
    }

    public OutOfMatrixException(Matrix A, int row, int col) {
        super("Row and Col out of bounds (row: " + row + ", num rows: " + A.getNumRows() +
                "), (col: " + col + ", num cols: " + A.getNumCols() + ")");
    }

    public OutOfMatrixException(Matrix A, String type, int position) {
        super(type + "out of bounds (at " + type + ": " + position + ", num " + type + "s: " + A.getNumRows() + ")");
    }
}
