package cardShufflingandDealing;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//DeckOfCards 类表示一副扑克牌
public class DeckOfCards {
   private List<Card> list; // 储存卡牌的数组
   private int top=0; //指向牌堆顶部的指针，用于抽牌
   int rounds=0; //用于记录轮次，每过三轮洗一次牌
   
   // 设置纸牌并洗牌
   public DeckOfCards() {
      Card[] deck = new Card[52];
      int count = 0; // 卡牌数

      // 用Card对象填充deck数组
      for (Card.Suit suit : Card.Suit.values()) {
         for (Card.Face face : Card.Face.values()) {  
            deck[count] = new Card(face, suit);
            ++count;
         } 
      } 

      list = Arrays.asList(deck); // 得到List    
      Collections.shuffle(list);  // 洗牌
   } 

   // 在四列中显示 52 张卡片
   public void printCards() {
      for (int i = 0; i < list.size(); i++) {
         System.out.printf("%-19s%s", list.get(i),
            ((i + 1) % 4 == 0) ? System.lineSeparator() : "");
      } 
   } 
   
   //抽牌，游戏进行三轮就要洗一次牌
   public List<Card> drawCards(int nums)
   {
	   List<Card> Drawed=new ArrayList<>(); 
	   for (int i = 0; i < nums; i++){
		   Drawed.add(list.get(top+i));
	   }
	   top+=nums;
	   
	   Collections.sort(Drawed, new Comparator<Card>(){
		   public int compare(Card c1,Card c2)
		   {
			   if(c1.getFace().ordinal()==c2.getFace().ordinal()) return 0;
			   if(c1.getFace().ordinal()>c2.getFace().ordinal()) return 1;
			   else return -1;
		   }
	   });
	   return Drawed;
   }
   
   //洗牌，top置0
   public void shuffer(){
	   Collections.shuffle(list);
	   top=0;
   }
   
   public static void main(String[] args)
   {
      DeckOfCards cards = new DeckOfCards();
      List<Card> five_cardPokerHand=cards.drawCards(5);
      Rule referee=new Rule();
      
      int[] gradeAndKey=referee.evaluate(five_cardPokerHand);//得到牌组的grade和key
      for (int i = 0; i < five_cardPokerHand.size(); i++)
      {
    	  five_cardPokerHand.get(i).printCard();
      }
      System.out.println();
      System.out.println("Grade:"+gradeAndKey[0]);
      System.out.println("Key:"+gradeAndKey[1]);
   }   
} 
