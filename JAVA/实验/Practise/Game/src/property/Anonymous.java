/*匿名类指的是在声明一个类的同时实例化它，使代码更加简洁精练
通常情况下，要使用一个接口或者抽象类，都必须创建一个子类

有的时候，为了快速使用，直接实例化一个抽象类，并“当场”实现其抽象方法。
既然实现了抽象方法，那么就是一个新的类，只是这个类，没有命名。
这样的类，叫做匿名类*/

package property;

public abstract class Anonymous {

	 String name;
	 
	 public abstract boolean disposable();//抽象方法
	 
	 public static void main(String[] args) {
	    Anonymous test=new Anonymous() {
	    	public boolean disposable() {//这里就不是抽象方法了
	    		return true;
	    	}
	    };
	    System.out.println(test.disposable());
	 }
}
