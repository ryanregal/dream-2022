
public class Spider extends Animal{
	//定义默认构造器，它调用父类构造器来指明所有的蜘蛛都是8条腿
	protected Spider(int legs) {
		super(legs);
	}
	
	public void eat() {
		System.out.println("蜘蛛吃掉！");
	}
}
