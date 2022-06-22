import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//抽象类MyShape
public abstract class MyShape {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private Color strokeColor;

	//无参构造函数
	public MyShape() {
		this(0, 0, 0, 0, Color.BLACK);
	}
	
	//带参构造函数
	public MyShape(double x1, double y1, double x2, double y2, Color strokeColor) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.strokeColor = strokeColor;
	}
	
	//绘图，抽象方法，只有声明，没有实现
	public abstract void draw(GraphicsContext gc);
	
	//Getters
	public double getX1() {
		return x1;
	}
	public double getY1() {
		return y1;
	}
	public double getX2() {
		return x2;
	}
	public double getY2() {
		return y2;
	}
	public Color getStrokeColor() {
		return strokeColor;
	}
	
	//Setters
	public void setX1(double x1) {
		this.x1 = x1 >= 0 ? x1 : 0;
	}
	public void setY1(double y1) {
		this.y1 = y1 >= 0 ? y1 : 0;
	}
	public void setX2(double x2) {
		this.x2 = x2 >= 0 ? x2 : 0;
	}
	public void setY2(double y2) {
		this.y2 = y2 >= 0 ? y2 : 0;
	}
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}
}