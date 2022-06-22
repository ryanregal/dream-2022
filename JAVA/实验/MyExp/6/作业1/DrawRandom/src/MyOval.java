import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//绘制椭圆的类
public class MyOval extends MyShape {
	//无参构造函数
	public MyOval() {
		this(0, 0, 0, 0, Color.BLACK);
	}

	//带参构造函数
	public MyOval(double x1, double y1, double x2, double y2, Color strokeColor)
	{
		super(x1, y1, x2, y2, strokeColor);//直接调用直接父类构造函数
	}

	//重写绘图方法
	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(getStrokeColor());
		gc.strokeOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
	}
	
	//Getters
	public double getUpperLeftX() {
		return getX1() < getX2() ? getX1() : getX2();
	}
	public double getUpperLeftY() {
		return getY1() < getY2() ? getY1() : getY2();
	}
	public double getWidth() {
		return Math.abs(getX1() - getX2());
	}
	public double getHeight() {
		return Math.abs(getY1() - getY2());
	}
}