package softwaredesign;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;

public class GUIBuilder {
    private TimerClass timer;
    private int timePerPuzzle;
    private Scene mainScene;
    private Scene resultScene;
    private Label[] bufferLabels;
    private Button[][] matrixButtons;
    public Stage window;

    public GUIBuilder (Stage window, GameManager gm) {
        this.mainScene = buildMainScene(gm);
        this.window = window;
    }

    public Scene getMainScene(){
        return this.mainScene;
    }

    private Scene buildMainScene(GameManager gm) {
        this.timePerPuzzle = gm.timePerPuzzle;

        //Creating main pane
        GridPane layoutPane = new GridPane();
        layoutPane.setStyle("-fx-background-color: black;");

        //Creating the internal panes
        GridPane matrixPane = matrixPaneBuilder(gm);
        GridPane sequencePane = sequencePaneBuilder(gm);
        GridPane timerPane = timerPaneBuilder(gm);
        GridPane controlPane = controlPaneBuilder(gm);
        GridPane bufferPane = bufferPaneBuilder(gm);

        //Adding style to internal panes
        sequencePane.setStyle("-fx-background-color: black; -fx-border-color: green;");

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

    private Scene buildResultScene(GameManager gm) {
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: black;");
        grid.setPrefHeight(200);
        grid.setPrefWidth(300);
        String labelTxt;
        if(gm.getResult()) labelTxt = "     YOU WIN";
        else labelTxt = "    YOU LOSE";
        Label label = new Label(labelTxt);
        label.setStyle("-fx-text-align: center; -fx-text-fill: green; -fx-font-size: 40");
        label.setPrefWidth(300);
        label.setPrefHeight(200);
        Label click = new Label("Click to restart");
        click.setStyle("-fx-text-align: center; -fx-text-fill: gray; -fx-font-size: 12");
        grid.add(label, 0, 0);
        grid.add(click, 0, 1);
        grid.setOnMouseClicked(e -> restartGame(gm));
        return new Scene(grid);
    }

    private GridPane matrixPaneBuilder(GameManager gm) {
        GridPane matrixPane = new GridPane();

        int matrixDim = gm.getCurrMatrixDims();
        matrixButtons = new Button[matrixDim][matrixDim];
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
                    for(int i = 0; i < matrixDim; i++) {
                        for (int j = 0; j < matrixDim; j++) {
                            setMatrixButtonStyle(gm, matrixButtons[j][i], j, i);
                        }
                    }
                    if(gm.isGameOver()) setResultScene(gm);
                });
                setMatrixButtonStyle(gm, matrixButtons[COL][ROW], COL, ROW);
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
        /*
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
        */
        int sequenceIndex = 0;
        for(String[] seq : gm.getSequencesIterator()) {
            String[] labelTxt = seq;
            StringBuilder labelTxtCombined = new StringBuilder();
            for (int i = 0; i < labelTxt.length; i++) {
                if (i == labelTxt.length-1) {
                    labelTxtCombined.append(labelTxt[i]);
                } else {
                    labelTxtCombined.append(labelTxt[i]).append("  ");
                }
            }
            seqLabels[sequenceIndex] = new Label(labelTxtCombined.toString());
            seqLabels[sequenceIndex].setPrefWidth(120);
            seqLabels[sequenceIndex].setPrefHeight(50);
            seqLabels[sequenceIndex].setStyle(textStyle);
            sequencePane.add(seqLabels[sequenceIndex], 0, sequenceIndex + 1);
            sequenceIndex++;
        }
        return sequencePane;
    }

    private GridPane timerPaneBuilder(GameManager gm) {
        //timer style
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

        Runnable everySec = () -> {
            Platform.runLater(() -> {
                String minutes = String.format("%02d : ", (this.timePerPuzzle % 3600)/60);
                String seconds = String.format("%02d", this.timePerPuzzle % 60);
                this.timePerPuzzle--;
                minutesTimer.setText(minutes);
                secondsTimer.setText(seconds);
            });
        };

        Runnable over = () -> {
            Platform.runLater(() -> {
                setResultScene(gm);
            });
        };

        this.timer = new TimerClass(this.timePerPuzzle, everySec, over);
        timer.run();

        return timerPane;
    }

    private GridPane controlPaneBuilder(GameManager gm) {
        GridPane controlPane = new GridPane();
        String normalStyle = "-fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;";

        Button undo = new Button("Undo");
        Button refresh = new Button("Refresh");

        undo.setPrefWidth(100);
        undo.setPrefHeight(30);
        undo.setStyle(normalStyle);

        undo.setOnAction(e -> {
            gm.undoMove();
            updateBufferDisplayValues(gm);
            for(int i = 0; i < gm.getCurrMatrixDims(); i++) {
                for (int j = 0; j < gm.getCurrMatrixDims(); j++) {
                    setMatrixButtonStyle(gm, matrixButtons[j][i], j, i);
                }
            }
        });

        undo.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        undo.setStyle("-fx-text-fill: black; -fx-background-color: white;");
                    }
                });

        undo.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        undo.setStyle(normalStyle);
                    }
                });

        //------------------------------------------------------------------

        refresh.setPrefWidth(100);
        refresh.setPrefHeight(30);
        refresh.setStyle(normalStyle);

        refresh.setOnAction(e -> {
            this.restartGame(gm);
        });

        refresh.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        refresh.setStyle("-fx-text-fill: black; -fx-background-color: white;");
                    }
                });

        refresh.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        refresh.setStyle(normalStyle);
                    }
                });



        controlPane.add(undo, 1, 0);
        controlPane.add(refresh, 0, 0);

        return controlPane;
    }

    private GridPane bufferPaneBuilder(GameManager gm) {
        GridPane bufferPane = new GridPane();
        int bufferLength = gm.getCurrBufferLength();
        this.bufferLabels = new Label[bufferLength];
        String textStyle = "-fx-text-fill: green; -fx-font-size: 16; -fx-border-color: green;";
        Label title = new Label("BUFFER:  ");
        title.setStyle("-fx-text-fill: green; -fx-font-size: 16;");
        bufferPane.add(title, 0, 0);
        for(int n = 0; n < bufferLength; n++) {
            String bufferElementTxt = gm.getCurrBufferValue(n);
            this.bufferLabels[n] = new Label(bufferElementTxt);
            this.bufferLabels[n].setPrefWidth(30);
            this.bufferLabels[n].setPrefHeight(50);
            this.bufferLabels[n].setStyle(textStyle);
            bufferPane.add(this.bufferLabels[n], n + 1, 0);
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

    private void setMatrixButtonStyle(GameManager gm, Button button, int col, int row) {
        String normalStyle = "-fx-background-color: black; -fx-text-fill: green; -fx-border-color: green;";
        String specialStyle = "-fx-background-color: green; -fx-text-fill: white; -fx-border-color: green;";
        String style;
        if (gm.getCurrAxis() == GameState.rowCol.COL && col == gm.getCurrNumRowCol()) {
            style = specialStyle;
            button.setStyle(style);
        } else if (gm.getCurrAxis() == GameState.rowCol.ROW && row == gm.getCurrNumRowCol()) {
            style = specialStyle;
            button.setStyle(style);
        } else {
            style = normalStyle;
            button.setStyle(style);
        }
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        button.setStyle("-fx-text-fill: black; -fx-background-color: white;");
                    }
                });

        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        button.setStyle(style);
                    }
                });
    }

    private void setResultScene(GameManager gm) {
        this.resultScene = buildResultScene(gm);
        this.window.setScene(this.resultScene);
    }

    private void restartGame(GameManager gm) {
        this.timer.timerTask.cancel();
        gm.restartGame();
        this.mainScene = buildMainScene(gm);
        this.window.setScene(this.mainScene);
    }
}
