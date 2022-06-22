package property;

public class MagicPotion extends Item{

	@Override
	public void effect() {
		 System.out.println("蓝瓶使用后，可以回魔");
	}
    public static void main(String[] args) {
        MagicPotion magicPotion = new MagicPotion();
        magicPotion.effect();
    }
}
