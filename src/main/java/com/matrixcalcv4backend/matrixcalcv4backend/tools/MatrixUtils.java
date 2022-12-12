package com.matrixcalcv4backend.matrixcalcv4backend.tools;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.InvalidMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.InvalidVectorException;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.OutOfMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.models.Matrix;
import org.apache.commons.lang3.math.Fraction;

public class MatrixUtils {
    public static Matrix mult(Matrix A, Matrix B) throws InvalidMatrixException, OutOfMatrixException, InvalidVectorException {
        if(A.getNumCols() != B.getNumRows())
            throw new InvalidMatrixException("multiplication", A, B);

        Matrix result = new Matrix(A.getNumRows(), B.getNumCols());

        for(int i = 0; i < A.getNumRows(); i++)
        {
            Fraction[] row = A.row(i);
            for(int j = 0; j < B.getNumCols(); j++)
                 result.setAt(i, j, dot(row, B.col(j)));
        }

        return result;
    }

    public static Matrix add(Matrix A, Matrix B) throws InvalidMatrixException, OutOfMatrixException {
        if(A.getNumRows() != B.getNumRows() || A.getNumCols() != B.getNumCols())
            throw new InvalidMatrixException("addition", A, B);

        int rows = A.getNumRows();
        int cols = B.getNumCols();

        Matrix result = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                result.setAt(i, j, A.getAt(i, j).add(B.getAt(i, j)));

        return result;
    }

    public static Matrix sub(Matrix A, Matrix B) throws InvalidMatrixException, OutOfMatrixException {
        if(A.getNumRows() != B.getNumRows() || A.getNumCols() != B.getNumCols())
            throw new InvalidMatrixException("subtraction", A, B);

        int rows = A.getNumRows();
        int cols = A.getNumCols();

        Matrix result = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                result.setAt(i, j, A.getAt(i, j).subtract(B.getAt(i, j)));

        return result;
    }

    public static Fraction dot(Fraction[] V, Fraction[] W) throws InvalidVectorException {
        if(V.length != W.length)
            throw new InvalidVectorException("dot-product", V, W);

        Fraction result = Fraction.getFraction(0);
        for(int i = 0; i < W.length; i++) result = result.add(V[i].multiplyBy(W[i]));

        return result;
    }

    public static Matrix rref(Matrix A) throws OutOfMatrixException {
        Matrix clone = A.deepCopy();
        Fraction[][] matrix = clone.getData();

        for(int j = 0; j < A.getNumCols() && j < A.getNumRows(); j++) { // For each column, starting from left
            for(int i = j; i < A.getNumRows(); i++) {  // Starting at row equal to current col
                if(!matrix[i][j].equals(Fraction.ZERO)) {  // If we find a non-zero term in the current col, move its row to the top and exit loop
                    Fraction[] temp = matrix[j];
                    matrix[j] = matrix[i];
                    matrix[i] = temp;
                    break;
                }
            }

            if(matrix[j][j].equals(Fraction.ZERO)) continue; // If col is all zeros, move to next col
            for(int i = j; i < A.getNumRows(); i++) {
                if(matrix[i][j].equals(Fraction.ZERO)) continue; //If leading number is 0, go to next row
                for(int k = j+1; k < A.getNumCols(); k++) {
                    matrix[i][k] = matrix[i][k].divideBy(matrix[i][j]); // Divide each row by its leading term
                }
                matrix[i][j] = Fraction.getFraction(1);
            }

            for(int i = 0; i < A.getNumRows(); i++) { // Subtract all rows below and above current row by multiple of its leading one
                if(i == j) continue;
                Fraction multiple = matrix[i][j].add(Fraction.ZERO);
                for(int k = j; k < A.getNumCols(); k++) {
                    matrix[i][k] = matrix[i][k].subtract(matrix[j][k].multiplyBy(multiple));
                }
            }
        }

        return clone;
    }
}
