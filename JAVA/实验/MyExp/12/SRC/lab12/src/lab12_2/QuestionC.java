package lab12_2;

public class QuestionC {
	public static void main(String[] args) {
		InterfaceC c=() -> "Welcome to lambdas!";
		System.out.print(c.testC());
	}
}
