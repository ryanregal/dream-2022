package charactor;
import property.Armor;
import property.Item;

//引用、构造方法、this指针,访问修饰符,初始化

/*类属性（相对于对象属性）：当一个属性被static修饰的时候，就叫做类属性，又叫做静态属性
当一个属性被声明成类属性，那么所有的对象，都共享一个值*/

/*类方法又叫做静态方法。对象方法又叫实例方法，非静态方法
访问一个对象方法，必须建立在有一个对象的前提的基础上
访问类方法，不需要对象的存在，直接就访问*/

/*对象属性初始化有3种 1. 声明该属性的时候初始化  2. 构造方法中初始化
3. 初始化块
声明→初始化块→构造方法*/

/*类转换为接口（向上转型）
   接口转换成实现类(向下转型)*/

/*类的多态:父类引用指向子类对象,需要如下条件
1. 父类（接口）引用指向子类对象
2. 调用的方法有重写*/

/*方法的重写是子类覆盖父类的对象方法。隐藏就是子类覆盖父类的类方法
 如果父类和子类创建的是静态方法，结果会调用引用所在的父类方法；
如果父类和子类创建的是非静态方法，结果会调用引用所指向的子类方法；*/

/*声明一个类的时候，默认是继承了Object
 Object类提供一个toString方法，所以所有的类都有toString方法
toString()的意思是返回当前对象的字符串表达*/

/*当一个对象没有任何引用指向的时候，它就满足垃圾回收的条件
当垃圾堆积的比较多的时候，就会触发垃圾回收,finalize() 方法就会被调用。
finalize() 不是开发人员主动调用的方法，而是由虚拟机JVM调用的。*/

/*equals() 用于判断两个对象的内容是否相同*/

/*final修饰类不能被继承，修饰方法不能被重写
 修饰基本类型变量，表示该变量只有一次赋值机会
final修饰引用，表示该引用只有1次指向对象的机会
String类是final修饰的，不能被继承*/

/*内部类分为四种：非静态内部类,静态内部类
匿名类,本地类*/

/*与非静态内部类不同，静态内部类的实例化 不需要一个外部类的实例为基础，可以直接实例化
语法：new 外部类.静态内部类();
因为没有一个外部类的实例，所以在静态内部类里面不可以访问外部类的实例属性和方法
除了可以访问外部类的私有静态成员外，静态内部类和普通类没什么大的区别*/

/*匿名类指的是在声明一个类的同时实例化它，使代码更加简洁精练
通常情况下，要使用一个接口或者抽象类，都必须创建一个子类
有的时候，为了快速使用，直接实例化一个抽象类，并“当场”实现其抽象方法。
既然实现了抽象方法，那么就是一个新的类，只是这个类，没有命名。
这样的类，叫做匿名类

比如假设Hero是抽象类，attack是抽象方法，那么可以在main方法中声明
Hero h1=new Hero{public void attack() {System.out.println("新的进攻手段");}}
这里h这个对象属于系统自动分配的匿名函数*/

/*本地类可以理解为有名字的匿名类
内部类与匿名类不一样的是，内部类必须声明在成员的位置，即与属性和方法平等的位置。
本地类和匿名类一样，直接声明在代码块里面，可以是主方法，for循环里等等地方
 class SomeHero extends Hero{public void attack() {System.out.println( name+ " 新的进攻手段"); }}*/

/*在匿名类中使用外部的局部变量，外部的局部变量必须修饰为final
 jdk会帮忙加，是因为匿名函数中的那个局部变量只是开始被赋值成外部的变量的值
 改变其值，并不会改变外部变量，所以加上final，让它看起来不能被修改*/


public class Hero {
	
    /*无修饰符的属性package/friendly/default,自己可以访问
           同包子类可以继承,不同包子类不能继承
           同包类可以访问,不同包类不能访问*/
	
    /* protected饰符的属性,自己可以访问
            同包子类可以继承,不同包子类可以继承
            同包类可以访问,不同包类不能访问*/

	//可以公开，直接访问，不会变化的值
	public static final int itemTotalNumber = 6;//物品栏的数量
	public String name="default"; //声明该属性的时候初始化
	public float hp; //血量
	float maxHP=100;	
	{maxHP=200;}//初始化块
	public static int itemCapacity=8;
	static{itemCapacity=6;}//静态初始化块 初始化
	public float armor; //护甲  
	public int moveSpeed=123; //移动速度
	public Armor InitItem;//初始盔甲 
	static String copyright;//类属性，静态属性
	
	public void attackHero(Hero h) throws EnemyHeroIsDeadException{
		if(h.hp==0) {
			throw new EnemyHeroIsDeadException(h.name +"已经挂了,不需要施放技能" );
		}
	}
	
    //敌方的水晶,静态内部类
    static class EnemyCrystal{
        int hp=5000;
         
        //如果水晶的血量为0，则宣布胜利
        public void checkIfVictory(){
            if(hp==0){
                Hero.battleWin();
                //静态内部类不能直接访问外部类的对象属性
            }
        }
    }
	
    // 非静态内部类，只有一个外部类对象存在的时候，才有意义
    // 战斗成绩只有在一个英雄对象存在的时候才有意义
    class BattleScore {
        int kill;
        int die;
        int assit;
 
        public void legendary() {
            if (kill >= 8)
                System.out.println(name + "超神！");
            else
                System.out.println(name + "尚未超神！");
        }
    }
	
	//父类的useItem方法
    public void useItem(Item i) {
        System.out.println("hero use item");
    }
	
	//实例方法,对象方法，非静态方法
    //必须有对象才能够调用
	public void die() {
		hp=0;
	}
	
	public void kill(Mortal m) {
		m.die();
	};
	
	//类方法，静态方法
    //通过类就可以直接调用
	public static void battleWin() {
		System.out.println("battle win");
	}
	
    //打印内存中的虚拟地址
    public void showAddressInMemory() {
    	System.out.println("打印this看到的虚拟地址："+this);
    }
    
    //参数名和属性名一样
    //在方法体中，只能访问到参数name
   //为了避免问题，参数名不得不使用其他变量名
   public void setName2(String heroName){
       name = heroName;
   }
   //也可以通过this访问属性
   public void setName3(String name){
       //name代表的是参数name
       //this.name代表的是属性name
       this.name = name;
   }
    
	public Hero() {
		System.out.println("Hero的构造方法 ");
	}
   
   public Hero(String name,float hp) {
       System.out.println("两个参数的构造方法");
       this.name = name;
       this.hp=hp;
   }
   
    //有参的构造方法
    //默认的无参的构造方法就失效了
    public Hero(String heroname,float heroHP,float heroArmor,int heroMoveSpeed) {
    	this(heroname,heroHP);//调用其他构造函数，必须放在最前面
    	System.out.println("四个参数的构造方法");
    	armor=heroArmor;
    	moveSpeed=heroMoveSpeed;
    }
    
    public static void main(String[] args) {
        //使用一个引用来指向这个对象
        Hero h = new Hero("金娜",100,50,100); 
    	System.out.println("实例化一个对象:"+" "+h.name+" "+h.hp+" "+h.armor+" "+h.moveSpeed);
    	
    	// 实例化内部类
        // BattleScore对象只有在一个英雄对象存在的时候才有意义
        // 所以其实例化必须建立在一个外部类对象的基础之上
        BattleScore score = h.new BattleScore();
        score.kill = 9;
        score.legendary();
        
        //实例化静态内部类
        Hero.EnemyCrystal crystal = new Hero.EnemyCrystal();
        crystal.checkIfVictory();
    	
    	Hero.copyright="版权由OUAT剧组所有";
    	System.out.println(Hero.copyright);
    	
    	h.die();//必须有一个对象才能调用
    	System.out.println(h.name+" "+h.hp+" "+h.armor+" "+h.moveSpeed);
    	Hero.battleWin(); //无需对象，直接通过类调用
    	System.out.println(Math.random());//random()就是一个类方法，直接通过类Math进行调用，并没有一个Math的实例存在。
    	
    	Hero newh=h; 
    	System.out.println(newh.maxHP);
    	
    	//把一个ADHero当做AD来使用，而AD接口只有一个physicAttack方法，这就意味着转换后就有可能要调用physicAttack方法，
    	//而ADHero一定是有physicAttack方法的，所以转换是能成功的。
    	ADHero ad=new ADHero();
    	//判断断一个引用所指向的对象，是否是Hero类型，或者Hero的子类
    	System.out.println(ad instanceof ADHero);
    	
    	System.out.println(h.toString());
    	
    	System.out.println(h.equals(newh));
    	
    	h.hp=0;
    	
    	try {
    		h.attackHero(newh);
    	}catch(EnemyHeroIsDeadException e) {
    		System.out.println("异常的具体原因："+e.getMessage());
    		e.printStackTrace();
    	}
    }  
}
