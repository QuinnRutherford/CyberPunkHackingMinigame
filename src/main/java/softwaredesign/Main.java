package softwaredesign;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final GameManager gm = new GameManager();

    public static void main (String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GUIBuilder guiBuilder = new GUIBuilder(primaryStage, gm);
        Scene scene = guiBuilder.getMainScene();
        //tell scene that new values are displayed

        primaryStage.setResizable(false);
        primaryStage.setTitle("CyberPunk Hacking Mini-game");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> { System.exit(0); });
    }
}
