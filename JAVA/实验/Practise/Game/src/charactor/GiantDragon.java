//单例模式:1. 构造方法私有化 2. 静态属性指向实例 3. public static的 getInstance方法，返回第二步的静态属性
//饿汉式单例模式，无论如何都会创建一个实例
//懒汉式单例模式，只有在调用getInstance的时候，才会创建实例
package charactor;

public class GiantDragon {
	
	//私有化构造方法使得该类无法在外部通过new 进行实例化
    private GiantDragon(){ }
 
    //准备一个类属性(保证只有一个大龙对象)，指向一个实例化对象。
    private static GiantDragon instance ;
     
    //public static 方法，提供给调用者获取instance对象
    public static GiantDragon getInstance(){
    	if(instance==null) instance=new GiantDragon();
        return instance;
    }
}
