
/*子类可以继承父类的对象方法,在继承后，重复提供该方法，就叫做方法的重写
又叫覆盖 override*/

package property;

public class Item {
    String name;
    int price;
    
    public void buy(){
        System.out.println("购买");
    }
    public void effect() {
        System.out.println("物品使用后，可以有效果");
    }
    @Override
    public String toString() {
    	return name+price;
    }
    @Override
    public void finalize(){
        System.out.println("Item正在回收");
    }
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Item)){
            return false;
        }else{
            Item h = (Item)o;
            return this.price == h.price;
        }
    }
    
    public static void main(String[] args) {
        Item i = new Item();
        i.effect();
         
        //继承
        LifePotion lp =new LifePotion();
        lp.effect();
        MagicPotion mp = new MagicPotion();
        mp.effect();

        //多态
        Item i1 = new LifePotion();
        Item i2 = new MagicPotion();
        i1.effect();
        i2.effect();

        i1.price = 20;
        i2.price = 20;

        //自己重写的equals
        System.out.println(i1.equals(i2));
        
    }
}