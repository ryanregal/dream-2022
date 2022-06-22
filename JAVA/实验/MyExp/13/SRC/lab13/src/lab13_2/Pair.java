package lab13_2;

import java.util.HashMap;
import java.util.Map;

//具有两个类型参数F和S的通用类对
//类头是公共类对<F，S>
public class Pair <F, S>
{
	private Map<F, S> elements;
	//构造函数，设置初始的HashMap的容量大小
	public Pair(int capacity)
	{
		int initCapacity = capacity > 0? capacity : 10;
		elements = new HashMap<F, S>(initCapacity);
	}
	
	public Pair()
	{
		this(10);
	}
	
	//为这对元素中的第一个元素和第二个元素添加get和set方法。
	public S get(F keyValue)
	{
		return elements.get(keyValue);//返回值，即S
	}
	public void set(F addKey, S addValue)
	{
		elements.put(addKey, addValue);//将键值对加入Map中
	}
}
