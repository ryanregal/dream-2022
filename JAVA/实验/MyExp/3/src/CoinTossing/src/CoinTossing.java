import java.security.SecureRandom;
import java.util.Scanner;

public class CoinTossing {
	//随机数生成器
	private static final SecureRandom random = new SecureRandom();
	//定义枚举变量，包含硬币正面和反面
	private enum Coin {
		HEADS, TAILS
	}

	public static void main(String[] args) {
		int headsCount = 0,tailsCount = 0,option = 0;
		Scanner scanner = new Scanner(System.in);
		String message = "\n选择菜单\n" + "扔硬币 (输入 1) \n" + "显示结果 (输入 2) \n" 
		+ "退出 (输入 3) \n"+ "请输入选择: ";

		while (option != 3) {
			System.out.print(message);//每次循环输出menu
			option = scanner.nextInt();//读取用户输入的选择

			switch (option) {
			case 1:
				if (toss() == Coin.HEADS) {
					headsCount++;//扔到正面
					System.out.printf("你扔到了正面%n");
				}
				else  {
					tailsCount++;//扔到反面
					System.out.printf("你扔到了反面%n");
				}
				
				break;
			case 2:
				display(headsCount, tailsCount);//展示结果
				break;
			case 3: break;
			default: System.err.println("\nError!\n");
			}
		}
		scanner.close();//关闭scanner
	}
	//打印结果方法
	public static void display(int headsCount, int tailsCount) {
		System.out.println("\nResults:\n");
		System.out.printf("HEADS: %d\n", headsCount);
		System.out.printf("TAILS: %d\n", tailsCount);
	}
	//单独的翻转硬币方法
	public static Coin toss() {
		//随机生成一个整数，值域 [0, 2)
		return random.nextInt(2) == 0 ? Coin.HEADS : Coin.TAILS;
	}
}
