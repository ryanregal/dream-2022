package cardShufflingandDealing;

//建立两个玩家对战
public class Test2
{
	public static void main(String[] args)
	{
		DeckOfCards cards=new DeckOfCards();
		Rule referee=new Rule();
		
		Player player1=new Player("\n玩家一");
		player1.five_cardHand=cards.drawCards(5);
		player1.gradeAndKey=referee.evaluate(player1.five_cardHand);
		
		Player player2=new Player("玩家二");
		player2.five_cardHand=cards.drawCards(5);
		player2.gradeAndKey=referee.evaluate(player2.five_cardHand);
		
		System.out.println(player1.getName()+"的牌:");
		player1.printHand();player1.printGradeAndKey();
		
		System.out.println("\n"+player2.getName()+"的牌:");
		player2.printHand();player2.printGradeAndKey();
		
		System.out.printf("\n%s胜利！\n",
		(referee.compareTwoPlayers(player1, player2)?
				player1.getName():player2.getName()));
	}

}
