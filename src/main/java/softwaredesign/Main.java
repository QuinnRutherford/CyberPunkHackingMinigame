package softwaredesign;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static int timePerPuzzle = 50; //In seconds
    private final GameManager gm = new GameManager();
    
    public static void main (String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GUIBuilder guiBuilder = new GUIBuilder(timePerPuzzle);
        Scene scene = guiBuilder.sceneBuilder(this.gm);

        primaryStage.setResizable(false);
        primaryStage.setTitle("CyberPunk Hacking Mini-game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
