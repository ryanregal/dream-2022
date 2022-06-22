package cardShufflingandDealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cardShufflingandDealing.Card.Face;
import cardShufflingandDealing.Card.Suit;

//player类表示玩家
public class Player
{
	public String name;
	List<Card> five_cardHand;
	public int[] gradeAndKey;
	
	public Player(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void printHand(){
		for (int i = 0; i <five_cardHand.size(); i++){
			five_cardHand.get(i).printCard();
		}
		System.out.println();
	}
	
	public void printGradeAndKey(){
		System.out.println("等级: "+gradeAndKey[0]
				+"  大小: "+gradeAndKey[1]);
	}
	
	//进行换牌
	public void change(List<Integer> indexs,DeckOfCards cards)
	{
		for (int i = 0; i < indexs.size(); i++){
			Collections.replaceAll(five_cardHand, five_cardHand.get(indexs.get(i)),cards.drawCards(1).get(0));
		}
		//排序函数
	    Collections.sort(five_cardHand, new Comparator<Card>(){
		    @Override
		    public int compare(Card c1,Card c2)
		    {
			    if(c1.getFace().ordinal()==c2.getFace().ordinal()) return 0;
			    if(c1.getFace().ordinal()>c2.getFace().ordinal()) return 1;
			    else  return -1;
		    }
	    });
	}
	
	//自动换牌
	public int autoChange(DeckOfCards cards)
	{
		int[] intFaces=new int[5];	//记录牌面的序号(1-13)
		Map<Face, Integer> faceCount=new HashMap<Face,Integer>();//记录牌面出现的张数
		Map<Suit, Integer> suiCount=new HashMap<>();
		Suit[] suits=new Suit[5]; //记录花色
		List<Integer> changeIndex=new ArrayList<>();
		
		//统计牌面以及花色,牌面花色通过数组的序号一一对应
		for (int i = 0; i < five_cardHand.size(); i++)
		{
		   Face face=five_cardHand.get(i).getFace();
		   Suit suit=five_cardHand.get(i).getSuit();
		   if(faceCount.containsKey(face))  faceCount.put(face, faceCount.get(face)+1);
		   else faceCount.put(face, 1);
		   if(suiCount.containsKey(suit))	suiCount.put(suit, suiCount.get(suit)+1);
		   else  suiCount.put(suit, 1);
		   
		   intFaces[i]=face.ordinal()+1;
		   suits[i]=suit;
		}
		switch (gradeAndKey[0])
		{
		case 1:{
			//高牌：
			if (suiCount.containsValue(4)){
				//四张同花色：换一张
				int index=0;
				Suit singleSuit=returnKey_suits(suiCount, 1).get(0);
				for (Card card : five_cardHand){
					if (singleSuit==card.getSuit()){
						changeIndex.add(index);
						break;
					}
					index++;
				}
				change(changeIndex, cards);
				break;
			}
			//差两张成顺子，换两张
			else if(!twoToFormAStraight(intFaces).isEmpty())
			{
				change(twoToFormAStraight(intFaces), cards);
				break;
			}
			else {
				//留牌面大的两张,换三张
				changeIndex.add(0);
				changeIndex.add(1);
				changeIndex.add(2);
				change(changeIndex, cards);
			}
			break;
		}
		case 2://一对
		{
			//留对子，换三张
			Face pairFace=returnKey_faces(faceCount, 2).get(0);
			int index=0;
			for (Card card:five_cardHand){
				if (card.getFace()!=pairFace){
					changeIndex.add(index);
				}
				index++;
			}
			change(changeIndex, cards);
			break;
		}
		case 3://两对
		{
			//留对子，换一张
			List<Face> pairFaces=returnKey_faces(faceCount, 2);
			int index=0;
			for (Card card : five_cardHand){
				if (card.getFace()!=pairFaces.get(1) &&card.getFace()!=pairFaces.get(0)){
					changeIndex.add(index);
				}
				index++;
			}
			change(changeIndex, cards);
			break;
		}
		case 4://三条
		{
			//留三条，换两张
			Face tripleFace=returnKey_faces(faceCount, 3).get(0);
			int index=0;
			for (Card card:five_cardHand){
				if (card.getFace()!=tripleFace){
					changeIndex.add(index);
				}
				index++;
			}
			change(changeIndex, cards);
			break;
		}
		default:
			break;//手牌已经够好不必处理
		}
		return changeIndex.size();
	}
	
   //返回key花色
   private List<Suit> returnKey_suits(Map<Suit, Integer> faceCount,int value){
	   List<Suit> list=new ArrayList<>();
	   for(Map.Entry<Suit,Integer> entry:faceCount.entrySet())
	   {
		   if (entry.getValue().equals(value))
		   {
			   list.add(entry.getKey());
		   }
	   }
	   return list;
   }
   
   //返回key面值
   private List<Face> returnKey_faces(Map<Face, Integer> faceCount,int value){
	   List<Face> list=new ArrayList<>();
	   for(Map.Entry<Face,Integer> entry:faceCount.entrySet())
	   	{
		   if (entry.getValue().equals(value))
		   {
			   list.add(entry.getKey());
		   }
	   }
		   return list;
   }

   //若把其中两张换掉有机会连成顺子，返回换掉的那两张的序号
   private List<Integer> twoToFormAStraight(int[] intFaces)
   {
	   List<Integer> changeList=new ArrayList<>();
	   for (int i = 0; i < five_cardHand.size(); i++){
		   for (int j = 0; j < five_cardHand.size(); j++){
			
			   int gap=0;
			   int[] intThreeCardFace=new int[3];
			   int tempIndex=0;
			   for (int k = 0; k < intFaces.length; k++){
				   //将五张牌中的四张取出放入临时数组
				   if(k==j||k==i) continue;
				   else{
					   intThreeCardFace[tempIndex]=intFaces[k];
					   tempIndex++;
				   }
			   }
			   for (int k = 0; k < intThreeCardFace.length-1; k++){
				   gap+=(intThreeCardFace[j+1]-intThreeCardFace[i]-1);
			   }
			   if(gap<=2){
				   changeList.add(i);
				   changeList.add(j);
				   return changeList;
			   }
			   changeList.clear();
		   }
	   }
	   return changeList;
   }
}
