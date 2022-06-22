package lab12_2;
import java.util.function.IntUnaryOperator;

public class QuestionE {

	public void getAnswer(IntUnaryOperator D,int input) {
    	System.out.println(D.applyAsInt(input));
    }
	public static void main(String[] args) {
		QuestionE e=new QuestionE();
		//这是 UnaryOperator 对  Integer 的原始类型特化。
		//这是一个函数接口，其函数方法是 applyAsInt(Int)。
		IntUnaryOperator cube1=a->a*a*a;
		e.getAnswer(cube1, 4);
		e.getAnswer(cube1, 3);
	}	
}
