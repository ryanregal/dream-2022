/*在类中声明一个方法，这个方法没有实现体，是一个“空”方法
这样的方法就叫抽象方法，使用修饰符“abstract"
当一个类有抽象方法的时候，该类必须被声明为抽象类*/

/*一旦一个类被声明为抽象类，就不能够被直接实例化*/

/*区别1：子类只能继承一个抽象类，不能继承多个
子类可以实现多个接口

区别2：抽象类可以定义public,protected,package,private
静态和非静态属性,final和非final属性
但是接口中声明的属性，只能是public,静态,final
即便没有显式的声明*/

package property;

public abstract class Treasure {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	// 抽象方法collect
    // 子类会被要求实现collect方法
    public abstract boolean disposable();
}
