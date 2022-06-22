package RationalNumbers;
import java.util.Scanner;

public class RationalTest {

	public static void main(String[] args) {
		
		//以a/b的形式打印有理数，其中a为分子，b为分母。
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入第一个有理数，分子在前，分母在后:");
		int r1=scanner.nextInt();
		int r2=scanner.nextInt();
		Rational test1 = new Rational(r1, r2);
		System.out.println("a/b形式打印该有理数：");
		System.out.println(test1);
		
		//以浮点格式打印有理数
		System.out.println("请决定浮点数的精度：");	
		int precision=scanner.nextInt();
		System.out.println("以浮点格式打印该有理数：");
		System.out.println(test1.toStringFloat(precision));
		
		//输入第二个有理数
		System.out.println("\n请输入第二个有理数，分子在前，分母在后（不可以是0）:");
		int r3=scanner.nextInt();
		int r4=scanner.nextInt();
		Rational test2 = new Rational(r3, r4);
		System.out.println("你输入的数为：");
		System.out.println(test2);
		
		//有理数加法
		System.out.println("\n第二个理数加第一个有理数：");
		Rational test4 = Rational.add(test2, test1);
		System.out.println(test4);
	
		//有理数减法
		System.out.println("第二个理数减第一个有理数：");
		Rational test3 = Rational.subtract(test2, test1);
		System.out.println(test3);
		
		//有理数乘法
		System.out.println("第二个理数乘第一个有理数：");
		Rational test5 = Rational.multiply(test2, test1);
		System.out.println(test5);
	
		//有理数除法
		System.out.println("第二个理数除第一个有理数：");
		Rational test6 = Rational.divide(test2, test1);
		System.out.println(test6);
		
		scanner.close();
	}
}