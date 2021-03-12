package softwaredesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {
    static int timePerPuzzle = 60; //In seconds

    public static void main (String[] args) {
        Thread gameThread = new Thread(new GameManager());
        gameThread.start();
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/resources/timer.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Timer");
        primaryStage.setScene(new Scene(root, 600, 200));
        primaryStage.show();
    }

}
