package com.matrixcalcv4backend.matrixcalcv4backend.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.matrixcalcv4backend.matrixcalcv4backend.models.Matrix;
import org.apache.commons.lang3.math.Fraction;

import java.io.IOException;

public class MatrixSerializer extends JsonSerializer<Matrix> {
    @Override
    public void serialize(Matrix matrix, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        String[][] data = new String[matrix.getNumRows()][matrix.getNumCols()];

        try {
            for(int i = 0; i < matrix.getNumRows(); i++)
                for(int j = 0; j < matrix.getNumCols(); j++)
                {
                    Fraction val = matrix.getAt(i, j);

                    if(val.doubleValue() == Math.rint(val.doubleValue()) || val.equals(Fraction.ZERO))
                        data[i][j] = Integer.toString(val.intValue());

                    else data[i][j] = val.toString();
                }
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }

        gen.writeStartObject();

        gen.writeObjectField("data", data);
        System.out.println("Wrote data");

        gen.writeNumberField("numRows", matrix.getNumRows());
        System.out.println("Wrote rows");

        gen.writeNumberField("numCols", matrix.getNumCols());
        System.out.println("Wrote cols");

        gen.close();
    }
}
