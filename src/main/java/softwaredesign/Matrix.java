package softwaredesign;

public final class Matrix {
    private final String[][] matrix;

    public Matrix(String matrixTxt) {
        String[] lines = matrixTxt.split("\n");
        String[] elements = lines[0].split(" ");
        String[][] newMatrix = new String[lines.length][elements.length];
        for(int i = 0; i < lines.length; i++){
            String[] tmpRow = lines[i].split(" ");
                if(i == 0) newMatrix = new String[lines.length][tmpRow.length];
            System.arraycopy(tmpRow, 0, newMatrix[i], 0, tmpRow.length);
        }
        this.matrix = newMatrix;
    }

    public String getMatrixElement(int row, int col) {
        return this.matrix[row][col];
    }

    public int getMatrixDims() {
        return this.matrix.length;
    }
}
