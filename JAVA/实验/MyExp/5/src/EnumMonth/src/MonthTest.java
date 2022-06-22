public class MonthTest {
	public static void main(String[] args)
	{
		//.values()将枚举类转变为一个枚举类型的数组,遍历输出
		for(Month.monthenmu i : Month.monthenmu.values())
		{
			System.out.println(i.getAbbr());
			System.out.println(i.getFullName());
		}
	}
}
