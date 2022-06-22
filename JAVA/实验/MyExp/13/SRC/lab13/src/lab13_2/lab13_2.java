/*编写一个具有两个类型参数F和S的通用类对，
 *每个参数分别表示对的第一个和第二个元素的类型。
 *为这对元素中的第一个元素和第二个元素添加get和set方法。
 *[提示：类头应该是公共类对<F，S>。]*/

package lab13_2;

public class lab13_2 
{
	public static void main(String[] args)
	{
		Pair<String, Integer> p1 = new Pair<>();//key为String，vale为Integer
		
		p1.set("计算机网络", 1);
		p1.set("计算机组成原理", 2);
		p1.set("实用操作系统", 3);
		
		System.out.println(p1.get("计算机网络"));
		System.out.println(p1.get("计算机组成原理"));
		System.out.println(p1.get("实用操作系统"));
	}
}
