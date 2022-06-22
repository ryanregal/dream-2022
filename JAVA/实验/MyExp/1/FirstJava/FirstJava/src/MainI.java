import java.util.*;//Scanner类需要的包

class MainI {
	public static void main(String args[]) {
		
		//第一种输出方法+第一种输入方法
		Scanner sc = new Scanner(System.in);//构造Scanner类对象
		System.out.println("Please enter your name:");
		String name = sc.nextLine();
		System.out.println("Please enter your age:");
		int age = sc.nextInt();
		System.out.println("Your name：" + name + "\n" + "Your age：" + age + "\n" );
		sc.close();
		
		//System.out的其他输出方法
		int num = 20;
		double price = 6.66;
		String s = "Welcome!";
		
		System.out.printf("%2$s 商品价格=%1$f，四舍五入后=%1$.1f\n", price, s);
		System.out.printf("商品个数为%1$d\n", num);
		
	}
}