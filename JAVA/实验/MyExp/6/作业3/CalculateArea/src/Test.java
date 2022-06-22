import java.util.Scanner;

//测试类，分别创建正方形类和三角形类的对象
//并调用各类中的 area() 方法，打印出不同形状的几何图形的面积
public class Test {
 
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		int n = Integer.parseInt(input.nextLine().trim());//表示图形个数
		for (int i=0;i<n;i++) 
		{
			//输入边数
			String s = input.nextLine().trim();
			String[] t = s.split(" ");
			//输入的是三角形
			if (t.length == 3) 
			{
				int a = Integer.parseInt(t[0]);
				int b = Integer.parseInt(t[1]);
				int c = Integer.parseInt(t[2]);
				Triangle tri = new Triangle(a,b,c);
				System.out.println(tri.area());
			}
			//输入的是长方形
			else if (t.length == 2) 
			{
				int a = Integer.parseInt(t[0]);
				int b = Integer.parseInt(t[1]);
				Rectangle rec = new Rectangle(a,b);
				System.out.println(rec.area());
			}
		}
		input.close();
	}
}