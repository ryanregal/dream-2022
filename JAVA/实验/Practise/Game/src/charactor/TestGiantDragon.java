package charactor;

public class TestGiantDragon {
	public static void main(String[] args) {
		GiantDragon g=GiantDragon.getInstance();
		GiantDragon g1=GiantDragon.getInstance();
		System.out.println(g==g1);
	}
	
}
