package softwaredesign;

import java.util.Vector;

public class Matrix {
    private String[][] matrix;

    public Matrix(String matrixTxt) {
        String[] lines = matrixTxt.split("\n");
        for(int i = 0; i < lines.length; i++){
            String[] tmpRow = lines[i].split(" ");
                if(i == 0) this.matrix = new String[lines.length][tmpRow.length];
            for(int j = 0; j < tmpRow.length; j++){
                this.matrix[i][j] = tmpRow[j];
            }
        }
    }

    private String[][] copyMatrix(String[][] original){
        String[][] copy = new String[original.length][original[0].length];
        for(int i = 0; i < original.length; i++){
            for(int j = 0; i < original[0].length; j++){
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    public String[][] getMatrix() {
        String[][] copy = copyMatrix(this.matrix);
        return copy;
    }

    public void printMatrix() {
        for(int i = 0; i < this.matrix.length; i++) {
            for(int j = 0; j < this.matrix[i].length; j++) {
                System.out.print(this.matrix[i][j]);
                if(j < this.matrix.length - 1) System.out.print("  ");
            }
            System.out.println();
        }
    }
}
