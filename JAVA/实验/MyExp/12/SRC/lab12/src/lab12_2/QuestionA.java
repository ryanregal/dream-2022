package lab12_2;
import java.util.function.IntConsumer;

public class QuestionA {
	
	/*编写一个可以传递给带有 IntConsumer(接口) 参数的方法的 lambda。 lambda 应显示其参数，后跟一个空格。*/	
	public void haveFun(IntConsumer fun,int value) {
    	fun.accept(value);
    }
	
	public static void main(String[] args) {
		QuestionA one=new QuestionA();
		//匿名方法
		IntConsumer con= new IntConsumer() {
			public void accept(int value) {
				System.out.printf("%d ",value);
			}
		};
		//参数和方法体之间加上->	
		IntConsumer con1= value->System.out.printf("%d ",value);;
		one.haveFun(con,2);
		one.haveFun(con1,3);
		one.haveFun(value->{System.out.printf("%d ",value);},3);
	}
}
