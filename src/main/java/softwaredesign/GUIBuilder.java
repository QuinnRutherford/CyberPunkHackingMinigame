package softwaredesign;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GUIBuilder {
    private int timePerPuzzle;
    private Scene mainScene;
    private Scene resultScene;
    private Label[] bufferLabels;

    public GUIBuilder (GameManager gm, final int timePerPuzzle) {
        this.timePerPuzzle = timePerPuzzle;
        this.bufferLabels = new Label[gm.getCurrBufferLength()];
        this.mainScene = buildMainScene(gm);
    }

    private Scene buildMainScene(GameManager gm) {
        //Creating main pane
        GridPane layoutPane = new GridPane();
        layoutPane.setStyle("-fx-background-color: black;");

        //Creating the internal panes
        GridPane matrixPane = matrixPaneBuilder(gm);
        GridPane sequencePane = sequencePaneBuilder(gm);
        GridPane timerPane = timerPaneBuilder();
        GridPane controlPane = controlPaneBuilder(gm);
        GridPane bufferPane = bufferPaneBuilder(gm);

        //Adding style to internal panes
        timerPane.setStyle("-fx-background-color: black;");
        sequencePane.setStyle("-fx-background-color: black; -fx-border-color: green;");
        bufferPane.setStyle("-fx-background-color: black;");

        //Positioning elements in the layout
        layoutPane.add(timerPane, 0, 0);
        layoutPane.add(getEmptyPane(), 0, 1); //empty pane for spacing
        layoutPane.add(matrixPane, 0, 2);
        layoutPane.add(getEmptyPane(), 0, 3); //empty pane for spacing
        layoutPane.add(getEmptyPane(), 1, 0); //empty pane for spacing
        layoutPane.add(bufferPane, 2, 0);
        layoutPane.add(sequencePane, 2, 2);
        layoutPane.add(controlPane, 2, 4);

        return new Scene(layoutPane, 600, 350);
    }

    //TODO: finish this to display when game ends
    private Scene buildResultScene() {
        GridPane grid = new GridPane();
        return new Scene(grid);
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
                final int ROW = row;
                final int COL = col;
                matrixButtons[col][row].setOnAction(e -> {
                    gm.addElementToBuffer(ROW, COL);
                    updateBufferDisplayValues(gm);
                });
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

    private GridPane controlPaneBuilder(GameManager gm) {
        GridPane controlPane = new GridPane();

        Button undo = new Button("Undo");
        Button refresh = new Button("Refresh");

        undo.setPrefWidth(100);
        undo.setPrefHeight(30);

        undo.setOnAction(e -> {
            gm.undoMove();
            updateBufferDisplayValues(gm);
        });

        refresh.setPrefWidth(100);
        refresh.setPrefHeight(30);

        //refresh.setOnAction();

        controlPane.add(undo, 1, 0);
        controlPane.add(refresh, 0, 0);

        return controlPane;
    }

    private GridPane bufferPaneBuilder(GameManager gm) {
        GridPane bufferPane = new GridPane();
        int bufferLength = gm.getCurrBufferLength();
        String textStyle = "-fx-text-fill: green; -fx-font-size: 16; -fx-border-color: green;";
        Label title = new Label("BUFFER:  ");
        title.setStyle("-fx-text-fill: green; -fx-font-size: 16;");
        bufferPane.add(title, 0, 0);
        for(int n = 0; n < bufferLength; n++) {
            String bufferElementTxt = gm.getCurrBufferValue(n);
            bufferLabels[n] = new Label(bufferElementTxt);
            bufferLabels[n].setPrefWidth(30);
            bufferLabels[n].setPrefHeight(50);
            bufferLabels[n].setStyle(textStyle);
            bufferPane.add(bufferLabels[n], n + 1, 0);
        }
        return bufferPane;
    }

    private void updateBufferDisplayValues(GameManager gm) {
        int bufferLength = gm.getCurrBufferLength();
        for(int n = 0; n < bufferLength; n++) {
            String bufferElementTxt = gm.getCurrBufferValue(n);
            this.bufferLabels[n].setText(bufferElementTxt);
        }
    }

    private Pane getEmptyPane() {
        Pane emptyPane = new Pane();
        emptyPane.setPrefWidth(20);
        emptyPane.setPrefHeight(20);
        return emptyPane;
    }

    public Scene getMainScene(){
        return this.mainScene;
    }

    public Scene getResultScene() {
        this.resultScene = buildResultScene();
        return this.resultScene;
    }
}
