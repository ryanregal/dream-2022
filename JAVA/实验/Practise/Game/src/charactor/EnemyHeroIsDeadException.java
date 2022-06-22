package charactor;
/*一个英雄攻击另一个英雄的时候，如果发现另一个英雄已经挂了，
 * 就会抛出EnemyHeroIsDeadException*/

/*创建一个类EnemyHeroIsDeadException*/

public class EnemyHeroIsDeadException extends Exception{
	public EnemyHeroIsDeadException() {
	}
	//带参的构造方法，并调用父类的对应的构造方法
	public EnemyHeroIsDeadException(String msg) {
		super(msg);
	}
}
