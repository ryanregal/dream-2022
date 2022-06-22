package property;


public class LifePotion extends Item{

	@Override
	public void effect() {
		 System.out.println("血瓶使用后，可以回血");
	}
	
	public static void main(String[] args) {
		Item i=new Item();
		i.effect();
		LifePotion lp =new LifePotion();
		lp.effect();
		lp.buy();
	}
}
