package softwaredesign;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    static int timePerPuzzle = 10; //In seconds
    private GameManager gm = new GameManager();
    
    public static void main (String[] args) {
        //Thread gameThread = new Thread(new GameManager());
        //gameThread.start();
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GUIBuilder guiBuilder = new GUIBuilder(timePerPuzzle);
        Scene scene = guiBuilder.sceneBuilder(this.gm, timePerPuzzle);

        //window creation
        Stage window = primaryStage;
        window.setResizable(false);
        window.setTitle("CyberPunk Hacking Mini-game");
        window.setScene(scene);
        window.show();
    }

}
