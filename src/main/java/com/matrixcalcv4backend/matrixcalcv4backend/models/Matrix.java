package com.matrixcalcv4backend.matrixcalcv4backend.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.matrixcalcv4backend.matrixcalcv4backend.deserialize.MatrixDeserializer;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.OutOfMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.serialize.MatrixSerializer;
import org.apache.commons.lang3.math.Fraction;

import java.util.Arrays;

@JsonDeserialize(using = MatrixDeserializer.class)
@JsonSerialize(using = MatrixSerializer.class)
public class Matrix {

    private final Fraction[][] data;
    private final int numRows;
    private final int numCols;

    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.data = new Fraction[numRows][numCols];
        for(int i = 0; i < numRows; i++)
            for(int j = 0; j < numCols; j++)
                this.data[i][j] = Fraction.ZERO;
    }

    public Matrix(String[][] data) {
        this.numRows = data.length;
        this.numCols = data[0].length;

        this.data = new Fraction[numRows][numCols];
        for(int i = 0; i < numRows; i++)
            for(int j = 0; j < numCols; j++)
                this.data[i][j] = Fraction.getFraction(data[i][j]);
    }

    public Fraction[] col(int col) throws OutOfMatrixException {
        validateCol(col);

        Fraction[] result = new Fraction[numRows];
        for(int i = 0; i < numRows; i++) result[i] = this.data[i][col];
        return result;
    }

    public Fraction[] row(int row) throws OutOfMatrixException {
        validateRow(row);
        return this.data[row];
    }

    public void setAt(int row, int col, double value) throws OutOfMatrixException {
        this.setAt(row, col, Fraction.getFraction(value));
    }

    public void setAt(int row, int col, Fraction value) throws OutOfMatrixException {
        validateRowAndCol(row, col);

        data[row][col] = value;
    }

    public Fraction getAt(int row, int col) throws OutOfMatrixException {
        validateRowAndCol(row, col);

        return data[row][col];
    }

    public void validateRowAndCol(int row, int col) throws OutOfMatrixException {
        if(row >= numRows && col >= numCols) throw new OutOfMatrixException(this, row, col);
        else if (row >= numRows) throw new OutOfMatrixException(this, "row", row);
        else if (col >= numCols) throw new OutOfMatrixException(this, "col", col);
    }

    public void validateRow(int row) throws OutOfMatrixException {
        if(row >= numRows) throw new OutOfMatrixException(this, "row", row);
    }

    public void validateCol(int col) throws OutOfMatrixException {
        if(col >= numCols) throw new OutOfMatrixException(this, "col", col);
    }

    public Matrix deepCopy() throws OutOfMatrixException {
        Matrix clone = new Matrix(this.numRows, this.numCols);
        for(int i = 0; i < numRows; i++)
            for(int j = 0; j < numCols; j++)
                clone.setAt(i, j, this.data[i][j].add(Fraction.ZERO));
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.deepEquals(data, matrix.data);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(data);
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public Fraction[][] getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "data=" + Arrays.deepToString(data) +
                ", numRows=" + numRows +
                ", numCols=" + numCols +
                '}';
    }
}
