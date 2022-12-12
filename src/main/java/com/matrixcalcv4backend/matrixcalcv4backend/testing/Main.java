package com.matrixcalcv4backend.matrixcalcv4backend.testing;

import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.InvalidMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.InvalidVectorException;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.OutOfMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.models.Matrix;
import com.matrixcalcv4backend.matrixcalcv4backend.tools.MatrixUtils;

import java.util.Arrays;
import java.util.Random;

//Commited??

public class Main {

    public static void main(String[] args) throws OutOfMatrixException, InvalidMatrixException, InvalidVectorException {
        testFour();
    }

    public static void testFour() throws OutOfMatrixException {
//        Matrix A = new Matrix(3, 3);
//        int counter = 1;
//        for(int i = 0; i < 3; i++)
//            for(int j = 0; j < 3; j++)
//                A.setAt(i, j, counter++);
//
//        Matrix B = MatrixUtils.rref(A);
//        System.out.println("A: " + A);
//        System.out.println("RREF of A: " + B);

        Random rand = new Random();

        Matrix C = new Matrix(3, 4);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 4; j++) C.setAt(i, j, 10-rand.nextInt(20));

        System.out.println("C: " + C);
        System.out.println("RREF of C: " + MatrixUtils.rref(C));
    }

    public static void testThree() throws OutOfMatrixException, InvalidMatrixException {

        double counter = 1;

        Matrix A = new Matrix(3, 3);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                A.setAt(i, j, counter++);

        counter = 1;
        Matrix B = new Matrix(3, 3);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                B.setAt(i, j, counter++);

        Matrix C = MatrixUtils.add(A, B);

        System.out.println(C);
    }

    public static void testTwo() throws OutOfMatrixException, InvalidMatrixException, InvalidVectorException {

        double counter = 1;

        Matrix A = new Matrix(3, 2);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 2; j++)
                A.setAt(i, j, counter++);

        counter = 1;

        Matrix B = new Matrix(2, 3);
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 3; j++)
                B.setAt(i, j, counter++);

        System.out.println(A);
        System.out.println(B);

        Matrix C = MatrixUtils.mult(A, B);
        System.out.println("[A] x [B] = C:");
        System.out.println(C);
    }

    public static void testOne() throws OutOfMatrixException {
        int rows = 4;
        int cols = 4;

        Matrix A = new Matrix(rows, cols);
        int counter = 1;
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++) A.setAt(i, j, counter++);

        System.out.println(A);

        for(int i = 0; i < rows; i++)
            System.out.println("Row " + i + ": " + Arrays.toString(A.row(i)));

        for(int j = 0; j < cols; j++)
            System.out.println("Col " + j + ": " + Arrays.toString(A.col(j)));
    }
}
