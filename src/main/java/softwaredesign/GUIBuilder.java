package softwaredesign;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUIBuilder {
    private int timePerPuzzle;
    public GUIBuilder (final int timePerPuzzle) {
        this.timePerPuzzle = timePerPuzzle;
    }

    public Scene sceneBuilder(GameManager gm, int timePerPuzzle) {
        GridPane layoutPane = new GridPane();
        GridPane matrixPane = matrixPaneBuilder(gm);
        GridPane sequencePane = sequencePaneBuilder(gm);
        GridPane timerPane = timerPaneBuilder();

        layoutPane.add(matrixPane, 0, 0);
        layoutPane.add(sequencePane, 0, 1);
        layoutPane.add(timerPane, 1, 0);

        Scene scene = new Scene(layoutPane, 500, 500);
        return scene;
    }

    public GridPane matrixPaneBuilder(GameManager gm) {
        GridPane matrixPane = new GridPane();

        int matrixDim = gm.getCurrMatrixDims();
        Button[][] matrixButtons = new Button[matrixDim][matrixDim];
        for(int row = 0; row < matrixDim; row++){
            for(int col = 0; col < matrixDim; col++){
                String buttonTxt = gm.getCurrMatrixValueAt(row, col);
                matrixButtons[col][row] = new Button(buttonTxt);
                //change button size
                matrixButtons[col][row].setPrefWidth(50);
                matrixButtons[col][row].setPrefHeight(50);
                final int ROW = row;
                final int COL = col;
                matrixButtons[col][row].setOnAction(e -> gm.AddElementToBuffer(ROW, COL));
                matrixPane.add(matrixButtons[col][row], col, row);
            }
        }
        return matrixPane;
    }

    public GridPane sequencePaneBuilder(GameManager gm) {
        GridPane sequencePane = new GridPane();

        int numberOfSeq = gm.getCurrNumOfSeq();
        Label[] seqLabels = new Label[numberOfSeq];
        for(int n = 0; n < numberOfSeq; n++){
            String[] labelTxt = gm.getCurrNSeq(n);
            String labelTxtCombined = "";
            for (int i = 0; i < labelTxt.length; i++) {
                if (i == labelTxt.length-1) {
                    labelTxtCombined += labelTxt[i];
                } else {
                    labelTxtCombined += labelTxt[i] + ", ";
                }
            }
            seqLabels[n] = new Label(labelTxtCombined);
            sequencePane.add(seqLabels[n], 0, n);
        }
        return sequencePane;
    }

    public GridPane timerPaneBuilder() {
        GridPane timerPane = new GridPane();

        Label minutesTimer = new Label();
        Label secondsTimer = new Label();
        timerPane.add(minutesTimer, 0,0);
        timerPane.add(secondsTimer, 1, 0);

        //Create animation
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            this.timePerPuzzle--;
            String minutes = String.format("%02d", (timePerPuzzle % 3600)/60);
            String seconds = String.format("%02d", timePerPuzzle % 60);
            minutesTimer.setText(minutes);
            secondsTimer.setText(seconds);

            if (timePerPuzzle <= 0) {
                timeline.stop();
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.playFromStart();

        return timerPane;
    }
}
