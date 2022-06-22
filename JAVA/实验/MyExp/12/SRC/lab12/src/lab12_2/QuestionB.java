package lab12_2;

public class QuestionB {
	public void answer2(InterfaceB b,String input) {
		System.out.print(b.test(input));
	}
	
	public static void main(String[] args) {
		QuestionB qb=new QuestionB();
		qb.answer2((String s) -> {return s.toUpperCase();},"questionb ");
		qb.answer2(String::toUpperCase,"to upper ");//引用静态方法
	}	
}
