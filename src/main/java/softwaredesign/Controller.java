package softwaredesign;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller {

    @FXML
    private Label minutesTimer;

    @FXML
    private Label secondsTimer;

    int secs = Main.timePerPuzzle;

    public void initialize() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            secs--;
            String minutes = String.format("%02d", (secs % 3600)/60);
            String seconds = String.format("%02d", secs % 60);
            minutesTimer.setText(minutes);
            secondsTimer.setText(seconds);

            if (secs <= 0) {
                timeline.stop();
                //game over gui;

            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.playFromStart();

    }

}
