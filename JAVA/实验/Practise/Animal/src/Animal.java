
public abstract class Animal {
	protected int legs;//动物腿的数目
	
	protected Animal(int legs) {
		this.legs=legs;
		System.out.println("腿的数量："+legs);
	}
	
    public Animal() {
        System.out.println("这个动物没有腿");
    }
	
	public abstract void eat() ;
	
	public void walk() {
		System.out.println("动物腿"+legs+"条");
	}
	
}
