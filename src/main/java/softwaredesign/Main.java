package softwaredesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Stack;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/resources/timer.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Timer");
        primaryStage.setScene(new Scene(root, 600, 200));
        primaryStage.show();
    }

    public static void main (String[] args){
        GameManager game = new GameManager();
        Application.launch(args);
        //Thread t1 = new Thread(new TimerClass(20));
        //t1.start();
    }
}
