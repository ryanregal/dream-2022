package bigHomework;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application
{
	public static Map<String, Object> controllers = new HashMap<String, Object>();
	public static Map<String, Object> globals = new HashMap<String, Object>();
	
	Global global = new Global();
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		globals.put("global", global);
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("MainPane.fxml"));
		fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root = fxmlLoader.load();
		primaryStage.setTitle("冰熊记事本");
		primaryStage.setScene(new Scene(root));
		primaryStage.getIcons().add(new Image("file:src/bigHomework/icon.png"));
		primaryStage.show();
		
		notepadController controller = fxmlLoader.getController();
		controller.mainStage(primaryStage);
		
		//当有关闭请求时
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent event)
			{
				if(controller.getStatus() != 1) {
					//创建确认对话框
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	                ImageView leaveIcon=new ImageView(this.getClass().getResource("leave.png").toString());
	                leaveIcon.setFitHeight(147.8);
	                leaveIcon.setFitWidth(106.4);
	                alert.setGraphic(leaveIcon);
	                alert.setTitle("关闭");
	                alert.setHeaderText("");
	                alert.setContentText("要离开了吗？");
	                Optional<ButtonType> result = alert.showAndWait();

	                if (result.get() == ButtonType.OK) 
	                {	
	                	//如果文件未保存
	                    if (controller.getStatus()!= 3 ) 
	                    {
		            		Alert alert0 = new Alert(AlertType.CONFIRMATION);
		            		alert0.setTitle("文件未保存");
		            		alert0.setHeaderText("");
		            		alert0.setContentText("你想保存文件吗？");
		            		ButtonType buttonTypeOne = new ButtonType("不保存啦");
		            		ButtonType buttonTypeTwo = new ButtonType("保存一下");
		            		ButtonType buttonTypeCancel = new ButtonType("返回", ButtonData.CANCEL_CLOSE);
		            		alert0.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,buttonTypeCancel);
		            		Optional<ButtonType> result1 = alert0.showAndWait();
		            		if (result1.get() == buttonTypeTwo) {
	            			  if (controller.getCurrentPath() == null)  controller.exitSave();
		                      else controller.save();
		            		}
	            			else if (result1.get() == buttonTypeCancel) {
	            				event.consume();
	            			}
		            		else primaryStage.close();
	                    }
	                   //如果保存，则直接关闭
	                   else primaryStage.close();
	                } 
	                else event.consume();//返回
				}
            }
		});
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
