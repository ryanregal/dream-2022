package lab10_3;
import java.util.Scanner;

public class phoneNumber 
{
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args)
	{
		System.out.println("Please enter the number in the format of [area code + phone number]\nFor example: (555)555-5555\n");
		String input = scan.nextLine();
		//先用)来分隔区号和电话号码
		String[] tokens = input.split("\\)");
		//"\D"	代表任意一个非数字字符
		//得到区号
		String[] areaCodeGroup = tokens[0].split("\\D");
		String areaCode = areaCodeGroup[1];
		//得到电话号码
		String[] phoneNumber = tokens[1].split("\\D");
		//打印结果
		System.out.println("Area code：" + areaCode);
		System.out.print("Phone number：");
		for(String str : phoneNumber)
		{
			System.out.print(str);
		}
		System.out.println();
	}
}
