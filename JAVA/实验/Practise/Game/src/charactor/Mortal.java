/*默认方法是JDK8新特性，指的是接口也可以提供具体方法了，而不像以前，只能提供抽象方法
Mortal 这个接口，增加了一个默认方法 revive，这个方法有实现体，并且被声明为了default

能够很好的扩展新的类，并且做到不影响原来的类*/

package charactor;

public interface Mortal {
	public void die();
	
    default public void revive() {
        System.out.println("本英雄复活了");
    }
}
