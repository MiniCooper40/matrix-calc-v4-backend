package com.matrixcalcv4backend.matrixcalcv4backend.controllers;

import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.OutOfMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.models.Matrix;
import com.matrixcalcv4backend.matrixcalcv4backend.tools.MatrixUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @Getter
    @Setter
    @NoArgsConstructor
    private class BinaryOperation{

        public Matrix A;
        public Matrix B;

        public BinaryOperation(Matrix A, Matrix B) {
            this.A = A;
            this.B = B;
        }
    }

    @PostMapping("/rref")
    public Matrix rref(@RequestBody Matrix A) throws OutOfMatrixException {
        System.out.println("Recieved matrix " + A);
        return MatrixUtils.rref(A);
    }
}
