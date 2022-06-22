import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdditionApp extends Application {
	@Override
	//重写start方法
	public void start(Stage stage) throws IOException {
		//使用FMXLLoader加载器，加载名为AdditionApp.fxml”的文件
		Parent root = FXMLLoader.load(getClass().getResource("AdditionApp.fxml"));
		Scene scene = new Scene(root);//创建一个场景
		stage.setTitle("Add");//为图形界面窗口设置标题
		stage.setScene(scene);//为图形界面窗口设置场景
		stage.show();//将图形界面设置为可见
	}
	public static void main(String[] args) {
		//通过Application抽象类的launch()方法启动程序
		launch(args);
	}
}
