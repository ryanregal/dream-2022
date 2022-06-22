package charactor;

public interface AD {
	public void physicAttack();
	default public void attack() {
		System.out.println("ADÓ¢ÐÛ¹¥»÷ÁË£¡");
	}
}
