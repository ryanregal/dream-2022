
public class Test {

	public static void main(String args[]) {
		Spider spider=new Spider(8);
		spider.eat();
		spider.walk();
		Cat cat=new Cat("è",4);
		cat.setName("Regina");
		cat.getName();
		cat.eat();
		cat.walk();
		cat.play();
		Fish fish=new Fish();
		fish.setName("Happy");
		fish.getName();
		fish.eat();
		fish.walk();
		fish.play();
	}
}
