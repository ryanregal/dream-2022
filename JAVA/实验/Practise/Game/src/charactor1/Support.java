package charactor1;

import charactor.Hero;

//重载
public class Support extends Hero implements Healer{
	@Override
	public void heal() {
		System.out.println(name+"进行了治疗");
	}
	public void heal(Hero h) {
		h.hp++;
		System.out.println(name+"对"+h.name+"进行了治疗");
	}
	public void heal(Hero h,int hp) {
		System.out.println(name+"对"+h.name+"进行了治疗");
		System.out.println("加血"+hp);
		h.hp+=hp;
	}
	/*static修饰的方法，无须产生类的实例对象就可以调用该方法，即static变量是存储在静态存储区的，不需要实例化。
	非static修饰的方法，需要产生一个类的实例对象才可以调用该方法。main是静态方法
	main函数中调用函数只能调用静态的。如果要调用非静态的，那么必须要先实例化对象，然后通过对象来调用非静态方法，
	*/
	public static void main(String args[]) {
		Support witch=new Support();
		witch.name="女巫";
		Hero one=new Hero();
		one.name="预言家";one.hp=0;
		witch.heal(one);
		witch.heal(one,100);
	}
}
