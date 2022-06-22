package cardShufflingandDealing;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//玩家与电脑对战
public class Test3
{
	public static void main(String[] args)
	{
		DeckOfCards cards=new DeckOfCards();//创建一副牌
		Rule referee=new Rule();//创建一个裁判
		Player player1=new Player("玩家");//创建一名玩家
		Player player2=new Player("电脑");//创建一个电脑玩家
		
		Scanner scanner=new Scanner(System.in);
		List<Integer> changeIndex=new ArrayList<>();//存放玩家准备换的牌的序号
		
		//玩家抽取初始手牌
		player1.five_cardHand=cards.drawCards(5);
		player2.five_cardHand=cards.drawCards(5);
		player1.gradeAndKey=referee.evaluate(player1.five_cardHand);
		player2.gradeAndKey=referee.evaluate(player2.five_cardHand);

		for(int rounds=0;rounds<8;rounds++)
		{
			System.out.println("\n你的牌:");
			player1.printHand();player1.printGradeAndKey();
			//提示是否换牌
			System.out.println("\n请输入要换的牌的下标\n"+ "(输入-1 不换牌):");
			String[] arr=scanner.nextLine().split(" ");
			for (int i = 0; i < arr.length; i++)
				changeIndex.add(Integer.parseInt(arr[i])-1);

			if (arr[0].equals("-1")||rounds==7)
			{
				if (rounds==7) System.out.println("达到最大的换牌次数!");
				System.out.println("\n你的牌:");
				player1.printHand();player1.printGradeAndKey();
				
				System.out.println("\n电脑的牌:");
				player2.printHand();player2.printGradeAndKey();
				System.out.printf("\n%s胜利！\n",referee.compareTwoPlayers(player1, player2)?
				player1.getName():player2.getName());
				break;
			}
			else //玩家和电脑同时换牌
			{
				player1.change(changeIndex, cards);
				changeIndex.clear();
				player1.gradeAndKey=referee.evaluate(player1.five_cardHand);
				System.out.printf("电脑更换了第 %d张牌\n",player2.autoChange(cards));
				player2.gradeAndKey=referee.evaluate(player2.five_cardHand);
			}
		}
		scanner.close();
	}

}
