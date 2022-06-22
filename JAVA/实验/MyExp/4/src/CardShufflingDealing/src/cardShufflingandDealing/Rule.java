package cardShufflingandDealing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cardShufflingandDealing.Card.Face;
import cardShufflingandDealing.Card.Suit;

//储存规则，相当于裁判类
public class Rule
{
	//比较两个牌手
   public boolean compareTwoPlayers(Player p1,Player p2)
   {
	   boolean p1Better=false;//true：p1更大
		if(p1.gradeAndKey[0]==p2.gradeAndKey[0]){ //等级相同时，比较牌值
			p1Better=p1.gradeAndKey[1]>p2.gradeAndKey[1]? true:false;
		}
		else {	//等级不同时，直接比较等级
			p1Better=p1.gradeAndKey[0]>p2.gradeAndKey[0]? true:false;
		}
		return p1Better;
   }
   
   //统计分析
   public int[] evaluate(List<Card> five_cardPokerHand)
   {
	   int[] gradeAndKey=new int[2]; //等级+牌面
	   int[] intFaces=new int[5]; //牌面序号
	   Map<Face, Integer> faceCount=new HashMap<Face,Integer>(); //牌面出现的张数
	   Suit[] suits=new Suit[5]; //记录花色
	   
	   //统计牌面以及花色,牌面花色通过数组的序号一一对应
	   for (int i = 0; i < five_cardPokerHand.size(); i++)
	   {
		   Face face=five_cardPokerHand.get(i).getFace();
		   Suit suit=five_cardPokerHand.get(i).getSuit();
		   if(faceCount.containsKey(face))
		   {
			   faceCount.put(face, faceCount.get(face)+1);
		   }
		   else 
		   {
			   faceCount.put(face, 1);
		   }
		   
		   intFaces[i]=face.ordinal()+1;
		   suits[i]=suit;
	   }
	  
	   //皇家同花顺
	   if(isStraight(intFaces)&&isFlush(suits) &&intFaces[0]==Face.Ace.ordinal()+1){
		   gradeAndKey[0]=10;
		   gradeAndKey[1]=suits[0].ordinal();
	   }
	   //同花顺
	   else if(isFlush(suits)&&isStraight(intFaces)){
		   gradeAndKey[0]=9;
		   gradeAndKey[1]=intFaces[4];
	   }
	   //四条
	   else if(isFOAK(faceCount)){   
		   gradeAndKey[0]=8;
		   gradeAndKey[1]=returnKey(faceCount,4).get(0).ordinal()+1;
	   }
	   //葫芦
	   else if (isFullHouse(faceCount)) {
		   gradeAndKey[0]=7;
		   gradeAndKey[1]=returnKey(faceCount,3).get(0).ordinal()+1;
	   }
	   //同花
	   else if(isFlush(suits)){
		   gradeAndKey[0]=6;
		   gradeAndKey[1]=intFaces[4];
	   }
	   //顺子
	   else if(isStraight(intFaces)){
		   gradeAndKey[0]=5;
		   gradeAndKey[1]=intFaces[4];
	   }
	   //三条
	   else if(isTOAK(faceCount)){
		   gradeAndKey[0]=4;
		   gradeAndKey[1]=returnKey(faceCount,3).get(0).ordinal()+1;
	   }
	   //两对
	   else if(isTwoPair(faceCount)){
		   gradeAndKey[0]=3;
		   List<Face> tempFaces=returnKey(faceCount, 2);
		   gradeAndKey[1]=tempFaces.get(0).ordinal()>tempFaces.get(1).ordinal()?
		   tempFaces.get(0).ordinal()+1:tempFaces.get(1).ordinal()+1;
		   
	   }
	   //一对
	   else if(isPair(faceCount)){
		   gradeAndKey[0]=2;
		   gradeAndKey[1]=returnKey(faceCount, 2).get(0).ordinal()+1;
	   }
	   // 高牌
	   else {
		   gradeAndKey[0]=1;
		   gradeAndKey[1]=intFaces[4];
	   }
	   return gradeAndKey;
   }
   
   //返回等级下的牌面值
   private List<Face> returnKey(Map<Face, Integer> faceCount,int value)
   {
	   List<Face> list=new ArrayList<>();
	   for(Map.Entry<Face,Integer> entry:faceCount.entrySet()){
		   if (entry.getValue().equals(value)){
			   list.add(entry.getKey());
		   }
	   }
		   return list;
   }
   
	//判断是否是顺子：五张数字相连
   private boolean isStraight(int[] intFaces)
   {
	   boolean isstraight=false;
	   for (int i = 0; i < intFaces.length-1; i++)
	   {
		   if(intFaces[i+1]-intFaces[i]-1!=0)
		   {
			   isstraight=false;
			   break;
		   }
	   }
	 //特殊情况A K Q J 10
	   if(intFaces[0]==1&&intFaces[1]==10&&intFaces[2]==11 &&intFaces[3]==12&&intFaces[4]==13)
		   isstraight=true;		
	   return isstraight;
   }
   
	//判断是否是同花：五张花色一样
   private boolean isFlush(Suit[] suits)
   {
	   boolean isflush=false;
	   if(suits[0]==suits[1]&&suits[1]==suits[2] &&suits[2]==suits[3]&&suits[3]==suits[4] &&suits[4]==suits[1]){
		  isflush=true; 
	   }
	   return isflush;
   }
   
	//判断是否是四条：四张一样
   private boolean isFOAK(Map<Face, Integer> faceCount)
   {
	   boolean isfoak=false;
	   if(faceCount.containsValue(4))  isfoak=true;
	   return isfoak;
   }
   
	//判断是否是葫芦：三张数字一样，另外两张数字一样
   private boolean isFullHouse(Map<Face, Integer> faceCount)
   {
	   boolean isfullhouse=false;
	   if(faceCount.containsValue(3)&&faceCount.containsValue(2))
		   isfullhouse=true;
	   return isfullhouse;
   }
   
	//判断是否是三条：5张中3张的数字一样
   private boolean isTOAK(Map<Face, Integer> faceCount)
   {
	   boolean istoak=false;
	   if(faceCount.containsValue(3)&&faceCount.size()==3)
		   istoak=true;
	   return istoak;
   }
   
	//判断是否是两对，4张中两两数字一样，余下1张不同   
   private boolean isTwoPair(Map<Face, Integer> faceCount)
   {
	   boolean istwopair=false;
	   if(faceCount.containsValue(2)&&faceCount.size()==3)
		   istwopair=true;
	   return istwopair;
   }
   
	//判断是否是一对： 5张中2张的数字一样，另外3张不相同
   private boolean isPair(Map<Face, Integer> faceCount)
   {
	   boolean ispair=false;
	   if(faceCount.containsValue(2)&&faceCount.size()==4)
		   ispair=true;
	   return ispair;
   }

}
