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

    public void printMatrix() {
        System.out.print("  ");
        for (int i = 0; i < this.matrix.length; i++) {
            System.out.print((i + 1) + "   ");
        }
        System.out.println();

        for(int i = 0; i < this.matrix.length; i++) {
            System.out.print((i + 1) + " ");

            for(int j = 0; j < this.matrix[i].length; j++) {

                System.out.print(this.matrix[i][j]);
                if(j < this.matrix.length - 1) System.out.print("  ");
            }
            System.out.println();
        }
    }
}
