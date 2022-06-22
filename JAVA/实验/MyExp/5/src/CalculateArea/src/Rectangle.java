//定义一个长方形类，继承自Graph，并且重新写了area方法
public class Rectangle extends Graph{
	double a;
	double b;
	Rectangle(double a,double b)
	{
		this.a = a;
		this.b = b;
	}
	//重写父类抽象方法
	public int area() 
	{
		return (int) Math.floor(a*b);
	}
}