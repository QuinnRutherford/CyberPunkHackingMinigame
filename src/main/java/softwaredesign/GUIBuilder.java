package softwaredesign;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class GUIBuilder {
    private int timePerPuzzle;

    public GUIBuilder (final int timePerPuzzle) {
        this.timePerPuzzle = timePerPuzzle;
    }

    public Scene sceneBuilder(GameManager gm) {
        GridPane layoutPane = new GridPane();
        layoutPane.setStyle("-fx-background-color: black;");
        GridPane matrixPane = matrixPaneBuilder(gm);
        GridPane sequencePane = sequencePaneBuilder(gm);
        GridPane timerPane = timerPaneBuilder();
        timerPane.setStyle("-fx-background-color: black; -fx-border-color: green;");
        sequencePane.setStyle("-fx-background-color: black; -fx-border-color: green;");

        Label space = new Label();
        space.setPrefHeight(1);
        space.setPrefWidth(20);
        layoutPane.add(space, 1, 0);

        layoutPane.add(timerPane, 0, 0);
        layoutPane.add(matrixPane, 0, 1);
        layoutPane.add(sequencePane, 2, 1);

        return new Scene(layoutPane, 550, 350);
    }

    private GridPane matrixPaneBuilder(GameManager gm) {
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
                matrixButtons[col][row].setId("BUTTON");
                final int ROW = row;
                final int COL = col;
                matrixButtons[col][row].setOnAction(e -> gm.AddElementToBuffer(ROW, COL));
                matrixPane.add(matrixButtons[col][row], col, row);
            }
        }
        return matrixPane;
    }

    private GridPane sequencePaneBuilder(GameManager gm) {
        GridPane sequencePane = new GridPane();
        String textStyle = "-fx-text-fill: green; -fx-font-size: 16;";

        Label titleLabel = new Label();
        titleLabel.setText("SEQUENCE REQUIRED TO UPLOAD: \n");
        titleLabel.setStyle(textStyle + "-fx-font-weight: bold;");
        sequencePane.add(titleLabel, 0, 0);

        int numberOfSeq = gm.getCurrNumOfSeq();
        Label[] seqLabels = new Label[numberOfSeq];
        for(int n = 0; n < numberOfSeq; n++){
            String[] labelTxt = gm.getCurrNSeq(n);
            StringBuilder labelTxtCombined = new StringBuilder();
            for (int i = 0; i < labelTxt.length; i++) {
                if (i == labelTxt.length-1) {
                    labelTxtCombined.append(labelTxt[i]);
                } else {
                    labelTxtCombined.append(labelTxt[i]).append("  ");
                }
            }
            seqLabels[n] = new Label(labelTxtCombined.toString());
            seqLabels[n].setPrefWidth(120);
            seqLabels[n].setPrefHeight(50);
            seqLabels[n].setStyle(textStyle);
            sequencePane.add(seqLabels[n], 0, n + 1);
        }
        return sequencePane;
    }

    private GridPane timerPaneBuilder() {
        GridPane timerPane = new GridPane();
        String textStyle = "-fx-text-fill: green; -fx-font-size: 16;";

        Label titleLabel = new Label();
        titleLabel.setText("BREACH TIME LEFT   ");
        titleLabel.setStyle(textStyle + "-fx-font-weight: bold;");

        Label minutesTimer = new Label();
        minutesTimer.setPrefWidth(50);
        minutesTimer.setPrefHeight(50);
        minutesTimer.setStyle(textStyle);

        Label secondsTimer = new Label();
        secondsTimer.setPrefWidth(50);
        secondsTimer.setPrefHeight(50);
        secondsTimer.setStyle(textStyle);

        timerPane.add(titleLabel, 0, 0);
        timerPane.add(minutesTimer, 1,0);
        timerPane.add(secondsTimer, 2, 0);

        //Create animation
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            this.timePerPuzzle--;
            String minutes = String.format("%02d : ", (timePerPuzzle % 3600)/60);
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
