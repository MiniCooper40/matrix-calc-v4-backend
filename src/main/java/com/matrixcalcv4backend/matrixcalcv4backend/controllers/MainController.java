package com.matrixcalcv4backend.matrixcalcv4backend.controllers;

import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.InvalidMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.InvalidVectorException;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.OutOfMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.models.Matrix;
import com.matrixcalcv4backend.matrixcalcv4backend.tools.MatrixUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    @Getter
    @Setter
    @NoArgsConstructor
    private static class BinaryOperation{

        public Matrix A;
        public Matrix B;

        public BinaryOperation(Matrix A, Matrix B) {
            this.A = A;
            this.B = B;
        }
    }

    @PostMapping("/rref")
    @CrossOrigin
    public Matrix rref(@RequestBody Matrix A) throws OutOfMatrixException {
        System.out.println("Recieved matrix " + A);
        return MatrixUtils.rref(A);
    }

    @PostMapping("/addition")
    @CrossOrigin
    public Matrix addition(@RequestBody BinaryOperation inputs) throws OutOfMatrixException, InvalidMatrixException {
        System.out.println("Recieved matrices: " + inputs.getA() + " and " + inputs.getB());
        return MatrixUtils.add(inputs.getA(), inputs.getB());
    }

    @PostMapping("/subtraction")
    @CrossOrigin
    public Matrix subtraction(@RequestBody BinaryOperation inputs) throws OutOfMatrixException, InvalidMatrixException {
        System.out.println("Recieved matrices: " + inputs.getA() + " and " + inputs.getB());
        return MatrixUtils.sub(inputs.getA(), inputs.getB());
    }

    @PostMapping("/multiplication")
    @CrossOrigin
    public Matrix multiplication(@RequestBody BinaryOperation inputs) throws OutOfMatrixException, InvalidMatrixException, InvalidVectorException {
        System.out.println("Recieved matrices: " + inputs.getA() + " and " + inputs.getB());
        return MatrixUtils.mult(inputs.getA(), inputs.getB());
    }
}
