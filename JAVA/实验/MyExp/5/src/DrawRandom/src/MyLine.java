import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//绘制直线的方法
public class MyLine extends MyShape {

	public MyLine() {
		super();//直接调用直接父类构造函数。
	}

	public MyLine(double x1, double y1, double x2, double y2, Color strokeColor) {
		super(x1, y1, x2, y2, strokeColor);//直接调用直接父类构造函数。
	}

	@Override
	//重写父类绘图方法
	public void draw(GraphicsContext gc) {
		gc.setStroke(getStrokeColor());
		gc.strokeLine(getX1(), getY1(), getX2(), getY2());
	}
}