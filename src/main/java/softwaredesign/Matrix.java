package softwaredesign;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private final List<List<String>> matrix;

    public Matrix(List<List<String>> matrix){
        this.matrix = new ArrayList<>(matrix);
    }

    public List<List<String>> getMatrix(){
        return new ArrayList<>(this.matrix);
    }
}
