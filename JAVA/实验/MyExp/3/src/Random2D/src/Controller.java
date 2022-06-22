import java.security.SecureRandom;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Controller {
	//用@FXML注解来表示属性可以被Fxml格式文件访问
	@FXML Pane pane; 
	private SecureRandom random = new SecureRandom();//随机数生成器
	
	//绘制圆圈 
	public void initialize () {
		for (int i = 0; i < 10; i++) {
			Circle circle = new Circle();//新建一个圆圈
			circle.setCenterX((this.random.nextInt(500) + 201));//随机设置圆圈的横坐标
			circle.setCenterY((this.random.nextInt(300) + 201));//随机设置圆圈的纵坐标
			circle.setRadius(this.random.nextInt(100));//随机设置圆圈的半径
			circle.setFill((Paint)randomColor());//随机填充圆圈的颜色
			this.pane.getChildren().add(circle);//添加圆圈到屏幕
		} 
	}
	//随机生成颜色
	private Color randomColor() {
		//参数分别代表red, green, blue, opacity)
		return Color.rgb(this.random.nextInt(256), this.random.nextInt(256), this.random.nextInt(256), 
				this.random .nextInt(101) / 100.0D);
	}
}

