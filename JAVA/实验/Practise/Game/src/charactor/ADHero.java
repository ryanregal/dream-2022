/*实现某个接口，相当于承诺了某种约定。
所以，实现了AD这个接口，就必须提供AD接口中声明的方法physicAttack()
实现在语法上使用关键字 implements*/

/*子类构造方法会被调用，其父类的构造方法也会被调用
并且是父类构造方法先调用，子类构造方法会默认调用父类的 无参的构造方法*/

/*当父类没有无参构造方法的时候( 提供了有参构造方法，并且不显示提供无参构造方法)，子类就会抛出异常，因为它尝试去调用父类的无参构造方法。
这个时候，必须通过super去调用父类声明的、存在的、有参的构造方法*/

package charactor;
import property.Item;

//重载
public class ADHero extends Hero implements AD,Mortal{
	
	public void die() {
		System.out.println("AD死去");
	}
	
	@Override
	public void physicAttack() {
		System.out.println(name+"进行了一次攻击");
	}
	
	public void attack(Hero... hero) {
		for(Hero i:hero) {
			System.out.println(name+"攻击了"+i.name);
		}
	}
	
    public int getMoveSpeed(){
        return this.moveSpeed;
    }
     
    public int getMoveSpeed2(){
        return super.moveSpeed;
    }
    
    int moveSpeed=400; //移动速度
	
	public ADHero() {}
	public ADHero(String heroname,float heroHP,float heroArmor,int heroMoveSpeed) {
		super(heroname,heroHP,heroArmor,heroMoveSpeed);//使用关键字super 显式调用父类带参的构造方法
		System.out.println("AD Hero的构造方法");
	}
	
    // 重写useItem，并在其中调用父类的userItem方法
    public void useItem(Item i) {
        System.out.println("adhero use item");
        super.useItem(i);
    }
	
	public static void main(String[]args) {
		//new ADHero();
		ADHero bh=new ADHero();
		bh.name="赏金猎人";
		Hero h1=new Hero();
		h1.name="盖伦";
		Hero h2 =new Hero();
		h2.name="提莫";
		bh.attack();
		bh.attack(h1);
		bh.attack(h1,h2);
        System.out.println(bh.getMoveSpeed());//ADHero也提供了属性moveSpeed
        System.out.println(bh.getMoveSpeed2());//通过super调用父类的moveSpeed属性
        Item i=new Item();
        bh.useItem(i);
	}
}
