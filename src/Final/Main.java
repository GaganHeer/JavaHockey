package Final;

/**
 * ACIT 2515 Final Exam
 * @author Gagan Heer, A00933997
 * @date April 18, 2017
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Vancouver Canucks");
        primaryStage.setScene(new Scene(root, 522, 358));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}