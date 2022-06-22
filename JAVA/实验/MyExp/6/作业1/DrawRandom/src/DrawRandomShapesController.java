import java.security.SecureRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class DrawRandomShapesController {
	private static final SecureRandom random = new SecureRandom();

	//用@FXML注解来表示属性可以被FXML格式文件访问
	@FXML
	private TextField xone;
	@FXML
	private TextField yone;
	@FXML
	private TextField xtwo;
	@FXML
	private TextField ytwo;
	@FXML
	private Canvas canvas;
	@FXML
	private int count=0;
	
	@FXML
	void drawShapesButtonPressed(ActionEvent event) {
		//当绘制超过20个图形停止绘制
		if(count>20) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			count=0;
		}
		else {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			generateShapes(gc);//生成图形
			count++;
		}
	}

	//生成图形
	private void generateShapes(GraphicsContext gc) {
		int x1,y1,x2,y2;
		x1 = Integer.parseInt(xone.getText());
		x2 = Integer.parseInt(xtwo.getText());
		y1 = Integer.parseInt(yone.getText());
		y2 = Integer.parseInt(ytwo.getText());
		if(x1>300||x1<0) {
			xone.setText("0<x1<300");//提示范围
			xone.selectAll();//选择输入文本
			xone.requestFocus();//光标进入控件
		}
		if(x2>300||x2<0) {
			xtwo.setText("0<x2<300");//提示范围
			xtwo.selectAll();//选择输入文本
			xtwo.requestFocus();//光标进入控件
		}
		if(y1>300||y1<0) {
			yone.setText("0<y1<300");//提示范围
			yone.selectAll();//选择输入文本
			yone.requestFocus();//光标进入控件
		}
		if(y2>300||y2<0) {
			ytwo.setText("0<y2<300");//提示范围
			ytwo.selectAll();//选择输入文本
			ytwo.requestFocus();//光标进入控件
		}
		
		//随机得到颜色
		Color strokeColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
			
		int shapeNumber = random.nextInt(3);//在0，1，2中得到一个随机数
		switch (shapeNumber) {
			case 0://0代表绘制线
				MyShape temp1=new MyLine(x1, y1, x2, y2, strokeColor);
				//动态绑定，调用子类MyLine中的draw方法
				temp1.draw(gc);
				break;
			case 1://1代表绘制三角形
				MyShape temp2=new MyRectangle(x1, y1, x2, y2, strokeColor);
				//动态绑定，调用子类MyRectangle中的draw方法
				temp2.draw(gc);
				break;
			case 2://2代表绘制椭圆
				MyShape temp3=new MyOval(x1, y1, x2, y2, strokeColor);
				//动态绑定，调用子类MyOval中的draw方法
				temp3.draw(gc);
				break;
			}
	}

}