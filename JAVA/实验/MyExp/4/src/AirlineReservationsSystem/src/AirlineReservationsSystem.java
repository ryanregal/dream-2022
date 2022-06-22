import java.util.Scanner;

public class AirlineReservationsSystem {
	private static final int TOTAL_SEATS = 11;//总共的位置
	private static boolean[] seats = new boolean[TOTAL_SEATS];//平面座位图

	private enum Class {
		FIRST, ECONOMIC
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int option = 0;
		int seatNumber = 0;
		while (option != 9) {
			System.out.print("请输入\n" + "一等座(1)  \n" + "经济座(2) \n" + "退出(0) \n"
					+ "选择: ");
			option = input.nextInt();
			switch (option) {
			case 1:
				seatNumber = checkAvailable(Class.FIRST);
				if (seatNumber != -1) {
					seats[seatNumber] = true;
					displayMessage(seatNumber, Class.FIRST);
				} 
				else { // 尝试经济型
					seatNumber = checkAvailable(Class.ECONOMIC);
					if (seatNumber != -1) {
						System.out.print(
								"\n一等座已满!\n你需要更换成经济座吗？\n1 - Yes\n2 - Not\n输入选择: ");
						option = input.nextInt();

						if (option == 1) {//选择经济座
							seats[seatNumber] = true;
							displayMessage(seatNumber, Class.ECONOMIC);
						} 
						else System.out.println("\n下一航班还有三小时\n");
					} 
					else System.out.println("\n没有更多座位了，航班已满\n");
				}
				break;
		
			 case 2:
				seatNumber = checkAvailable(Class.ECONOMIC);

				if (seatNumber != -1) {
					seats[seatNumber] = true;
					displayMessage(seatNumber, Class.ECONOMIC);
				} 
				else { // try in another section
					seatNumber = checkAvailable(Class.FIRST);

					if (seatNumber != -1) {
						System.out.print(
								"\n经济座已满!\\n你需要更换成一等座吗？\n1 - Yes\n2 - Not\n输入选择: ");
						option = input.nextInt();

						if (option == 1) {//选择一等座
							seats[seatNumber] = true;
							displayMessage(seatNumber, Class.FIRST);
						} else
							System.out.println("\n下一航班还有三小时\n");
					} else
						System.out.println("\n没有更多座位了，航班已满\n");
				}
				break;
				
			case 0:	break;
			default:
				System.out.println("\n请输入正确编码\n");
				break;
		  }
		}
		input.close();
	}

	//输出座位信息
	public static void displayMessage(int seatNumber, Class section) {
		System.out.println("\n登机卡");
		System.out.printf("座位号: %d%n类别: %s%n%n", seatNumber, section);
	}

	//检查可以使用的座位
	public static int checkAvailable(Class section) {
		int seatNumber = -1;//如果最后等于-1则表示没有可以使用的座位

		switch (section) {
		case FIRST:
			for (int i = 1; i <= TOTAL_SEATS / 2; i++) {
				if (seats[i] == false) {//座位还没有被占用
					seatNumber = i;
					break;
				}
			}
			break;
		case ECONOMIC:
			for (int i = TOTAL_SEATS / 2 + 1; i < TOTAL_SEATS; i++) {
				if (seats[i] == false) {//座位还没有被占用
					seatNumber = i;
					break;
				}
			}
			break;
		}
		return seatNumber;
	}
}