/*编写一个EqualTo简单的泛型版本的方法，将其两个参数与相等的方法进行比较，
 * 如果它们相等则返回true，否则返回false。
 * 在调用各种内置类型的isEqualTo的程序中使用此通用方法，如对象或整数. */

package lab13_1;

public class lab13_1 
{
	public static boolean isEqualTo(Object v4, Object v5)
	{
		if(v4.equals(v5)) return true;
		else  return false;
	}

	public static void main(String[] args)
	{
		//Integer 类型
		Integer v1 = 1, v2 = 1, v3 = 0;
		if(isEqualTo(v1, v2))   System.out.println("v1 equals to v2");
		else System.out.println("v1 doesn't equal to v2");
		if(isEqualTo(v1, v3))	System.out.println("v1 equals to v3");
		else  System.out.println("v1 doesn't equal to v3");
		
		//Double 类型
		Double v4 = new Double(2.0);
		Double v5 = new Double(2.0);
		Double v6 = new Double(2.1);
		
		if(isEqualTo(v4, v5))   System.out.println("v4 equals to v5");
		else	System.out.println("v4 doesn't equal to v5");
		if(isEqualTo(v5, v6))	System.out.println("v5 equals to v6");
		else    System.out.println("v5 doesn't equal to v6");
		
		//Object 类型
		Object v7 = new Double(2.0);
		Object v8 = new Double(2.0);
		Object v9 = new Double(2.1);
		
		if(isEqualTo(v7, v8))   System.out.println("v7 equals to v8");
		else	System.out.println("v7 doesn't equal to v8");
		if(isEqualTo(v8, v9))	System.out.println("v8 equals to v9");
		else    System.out.println("v8 doesn't equal to v9");
	}
}
