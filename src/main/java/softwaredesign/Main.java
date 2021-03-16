package softwaredesign;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    static int timePerPuzzle = 60; //In seconds
    private GameManager gm = new GameManager();
    
    public static void main (String[] args) {
        //Thread gameThread = new Thread(new GameManager());
        //gameThread.start();
        GameManager gm = new GameManager();
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //window creation
        Stage window = primaryStage;
        window.setTitle("Window");

        //scene creation
        GridPane layout = new GridPane();

        //matrix button creation
        int matrixDim = this.gm.getCurrMatrixDims();
        Button[][] matrixButtons = new Button[matrixDim][matrixDim];
        for(int row = 0; row < matrixDim; row++){
            for(int col = 0; col < matrixDim; col++){
                String buttonTxt = this.gm.getCurrMatrixValueAt(row, col);
                matrixButtons[col][row] = new Button(buttonTxt);
                final int ROW = row;
                final int COL = col;
                matrixButtons[col][row].setOnAction(e -> this.gm.getCurrMatrixValueAt(ROW, COL));
                layout.add(matrixButtons[col][row], row, col);
            }
        }

        //sequences label creation
        int numberOfSeq = this.gm.getCurrNumOfSeq();
        Label[] seqLabels = new Label[numberOfSeq];
        for(int n = 0; n < numberOfSeq; n++){
            String[] labelTxt = this.gm.getCurrNSeq(n);
            String labelTxtCombined = "";
            for (int i = 0; i < labelTxt.length; i++) {
                if (i == labelTxt.length-1) {
                    labelTxtCombined += labelTxt[i];
                } else {
                    labelTxtCombined += labelTxt[i] + ", ";
                }
            }
            seqLabels[n] = new Label(labelTxtCombined);
            layout.add(seqLabels[n], matrixDim + 1, n + matrixDim);
        }

        //show window
        Scene scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.show();
    }

}
