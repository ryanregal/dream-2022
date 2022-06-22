package charactor;

/*作为同时继承了AD和AP中的 默认方法attack，就必须在实现类中重写该方法
从而免去到底调用哪个接口的attack方法这个模棱两可的问题*/

public interface AP {
	//是没有方法体，是一个“空”方法
	public void magicAttack() ;
}
