//定义一个三角形类，继承自Graph，并且重新写了area方法
public class Triangle extends Graph{
	double a;
	double b;
	double c;
	Triangle(int a,int b,int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	//重写父类抽象方法
	public int area() {
		double p = (a+b+c)/2.0;
		return (int) Math.floor(Math.sqrt(p*(p-a)*(p-b)*(p-c)));
	}
}
