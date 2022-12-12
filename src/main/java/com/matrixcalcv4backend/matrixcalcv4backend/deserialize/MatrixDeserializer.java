package com.matrixcalcv4backend.matrixcalcv4backend.deserialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.matrixcalcv4backend.matrixcalcv4backend.exceptions.InvalidMatrixException;
import com.matrixcalcv4backend.matrixcalcv4backend.models.Matrix;

import java.io.IOException;

public class MatrixDeserializer extends JsonDeserializer<Matrix> {
    @Override
    public Matrix deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonNode root = p.getCodec().readTree(p);

        String[][] data;

        try {
            data = ctxt.readTreeAsValue(root.get("data"), String[][].class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot find \"data\" field");
        }

        int numRows = data.length;
        for(int i = 0; i < numRows-1; i++) {
            if(data[i].length != data[i+1].length)  {
                throw new IllegalArgumentException("Invalid matrix sizing (inconsistent row length)");
            }
        }

        return new Matrix(data);
    }
}
