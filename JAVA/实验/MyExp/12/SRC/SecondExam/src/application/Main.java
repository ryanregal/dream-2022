package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	   public void start(Stage stage) throws Exception 
	   {
	      Parent root =  FXMLLoader.load(getClass().getResource("text.fxml"));

	      Scene scene = new Scene(root); // attach scene graph to scene
	      stage.setTitle("Text"); // displayed in window's title bar
	      stage.setScene(scene); // attach scene to stage
	      stage.show(); // display the stage
	   }

	   public static void main(String[] args) {
	      launch(args); 
	   }
}
