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


    int secs = 65;//Main.tPerLevel;


    public void initialize() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            secs--;
            String minutes = String.format("%02d", (secs % 3600)/60);
            String seconds = String.format("%02d", secs % 60);
            minutesTimer.setText(minutes);
            secondsTimer.setText(seconds);

            if (secs <= 0) {
                time.stop();
                //game over gui;
            }
        });

        time.getKeyFrames().add(frame);
        time.playFromStart();

    }

}
