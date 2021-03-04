package softwaredesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public static void main (String[] args){
        //GameManager game = new GameManager();
        //game.runGameLoop();
        Thread gameThread = new Thread(new GameManager());
        Thread timerThread = new Thread(new TimerClass(30));
        gameThread.start();
        timerThread.start();
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
