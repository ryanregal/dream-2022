
public class Fish extends Animal implements Pet{
	
	private String name;
	public void walk() {
		System.out.println("鱼不可以走路！");
	}
	public Fish() {
		super();
	}
	public void eat() {
		System.out.println("鱼吃！");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void play() {
		System.out.println("鱼玩！");
	}
}
