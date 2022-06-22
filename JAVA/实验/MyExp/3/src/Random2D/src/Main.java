import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {
	public static void main(String[] args) {
		//通过Application抽象类的launch()方法启动程序
		launch(args);
	}
	//重新start方法
	public void start(Stage stage) throws Exception {
		//使用FMXLLoader加载器，加载名为“sample.fxml”的文件
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("sample.fxml"));
		Scene scene = new Scene(root);//创建一个场景
		stage.setTitle("Main");//为图形界面窗口设置标题
		stage.setScene(scene);//为图形界面窗口设置场景
		stage.show();//将图形界面设置为可见
	}
}
