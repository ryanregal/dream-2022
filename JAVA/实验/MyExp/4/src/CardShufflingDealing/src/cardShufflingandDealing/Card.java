package cardShufflingandDealing;

//Card 类代表一张扑克牌
class Card {
   public enum Face {Ace, Deuce, Three, Four, Five, Six,
      Seven, Eight, Nine, Ten, Jack, Queen, King }            
   public enum Suit {Clubs, Diamonds, Hearts, Spades} 
   
   private String[] faceStrings= {"A","2","3","4","5","6","7",
		   "8","9","10","J","Q","K"};
   private String[] suitString= {"C","D","H","S"};

   private  Face face; 
   private  Suit suit; 
   
	// 两参数的构造函数初始化卡片的面和花色
   public Card(Face face, Suit suit) {
       this.face = face; 
       this.suit = suit; 
   } 
   
   // return face、suit of the card
   public Face getFace() {return face;}
   public Suit getSuit() {return suit;}
   
   // set face、suit of the card
   public void setFace(Face face){this.face=face;}
   public void setSuit(Suit suit){ this.suit=suit;}

	//返回Card 的字符串表示形式
   public String toString() {
      return String.format("%s of %s", face, suit);
   }
   
   //打印卡牌
   public void printCard(){
	   System.out.print(faceStrings[this.face.ordinal()]
			   +suitString[this.suit.ordinal()]+"  ");
   }
} 
