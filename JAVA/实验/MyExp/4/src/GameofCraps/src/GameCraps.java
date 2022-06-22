import java.security.SecureRandom;
import java.util.Scanner;
 
	public class GameCraps 
	{
	   // create secure random number generator for use in method rollDice
	   private static final SecureRandom randomNumbers = new SecureRandom();
 
	   // enum type with constants that represent the game status
	   private enum Status {CONTINUE, WON, LOST};
 
	   // constants that represent common rolls of the dice
	   private static final int SNAKE_EYES = 2;
	   private static final int TREY = 3;
	   private static final int SEVEN = 7;
	   private static final int YO_LEVEN = 11;
	   private static final int BOX_CARS = 12;
	   
	   private static int winTimes = 0; //游戏胜利的次数
	   private static int stepsTillWinTotal = 0; //赢得游戏所用总步数
	   private static int stepsTillLoseTotal = 0;//输掉游戏所用总步数
	   private static int stepsContinueTotal = 0;
	   private static int stepsContinueSinglePlay = 0;
	   private static double winPercentage = 0.0; //赢得游戏的概率
	   private static double averageLength = 0.0; //平均每局游戏所用步数
	   private static int[] stepsTillWinFrequency = new int[21]; //走x步的赢局数
	   private static int[] stepsTillLoseFrequency = new int[21]; //走y步的赢局数
	   
	   public static void main(String[] args)
	   {
			int playTimes=0;
			Scanner input=new Scanner(System.in);
			
			do{
				initialize(); //初始化
				System.out.println("---------------Start--------------------");
				System.out.print("请输入游戏次数（输入0表示退出）：");
				playTimes =input.nextInt();
				if(playTimes ==0){
					System.out.printf("游戏结束");
					break;
				}
					
				for (int i=0;i<playTimes; i++) {
					playCraps();   //模拟游戏
				}	
				//计算统计值
				winPercentage = (double)winTimes/playTimes*100;
				averageLength = (double)(stepsTillWinTotal + stepsTillLoseTotal)/playTimes;
				
				System.out.printf("游戏进行共%d轮：%n", playTimes);
				System.out.printf("你赢得游戏%d轮，获胜的概率为：%.2f%%%n", winTimes, winPercentage);
				System.out.printf("%n平均每局游戏所用步数：%.2f%n", averageLength);
				System.out.printf("赢得游戏所用总步数：%d%n", stepsTillWinTotal);
				System.out.printf("输掉游戏所用总步数：%d%n", stepsTillLoseTotal);
				System.out.printf("%n赢得/输掉一轮游戏所用步数分布如下：%n%n");
				for (int i=1; i<20; i++) {
					System.out.printf("走%2d步：\t赢得游戏：%6d轮    输掉游戏：%6d轮\t%n",i,stepsTillWinFrequency[i],
							stepsTillLoseFrequency[i]);
				}
				System.out.printf("%2d以上：\t赢得游戏：%6d轮    输掉游戏：%6d轮\t%n",20,stepsTillWinFrequency[20],
						stepsTillLoseFrequency[20]);
			}while (playTimes != -1);
			input.close();
	   }
 
	   // plays games of craps
	   public static void playCraps()
	   {
		  stepsContinueSinglePlay = 0; //每局游戏开始时重置为0
	      int myPoint = 0; // point if no win or loss on first roll
	      Status gameStatus; // can contain CONTINUE, WON or LOST
 
	      int sumOfDice = rollDice(); // first roll of the dice
 
	      // determine game status and point based on first roll 
	      switch (sumOfDice) 
	      {
	         case SEVEN: // win with 7 on first roll
	         case YO_LEVEN: // win with 11 on first roll           
	            gameStatus = Status.WON;
	            ++stepsTillWinTotal;//赢得游戏所用步数加1
	            ++stepsTillWinFrequency[1];//赢局的游戏轮数加1
	            break;
	         case SNAKE_EYES: // lose with 2 on first roll
	         case TREY: // lose with 3 on first roll
	         case BOX_CARS: // lose with 12 on first roll
	            gameStatus = Status.LOST;
	            ++stepsTillLoseTotal;//输掉游戏所用步数加1
	            ++stepsTillLoseFrequency[1];//输局的游戏轮数加1
	            break;
	         default: // did not win or lose, so remember point         
	            gameStatus = Status.CONTINUE; // game is not over
	            myPoint = sumOfDice; // remember the point
	            ++stepsContinueTotal;
	     	    ++stepsContinueSinglePlay;
	            break;
	      } 
 
	      // while game is not complete
	      while (gameStatus == Status.CONTINUE) // not WON or LOST
	      { 
	         sumOfDice = rollDice(); // roll dice again
 
	         // determine game status
	         if (sumOfDice == myPoint) { // win by making point
	        	gameStatus = Status.WON;
	        	 ++stepsTillWinTotal; //赢局步数+1
	        	//如果大于20步，则全部储存在20下标处
	        	 ++stepsTillWinFrequency[++stepsContinueSinglePlay<20 ? stepsContinueSinglePlay: 20];
	         }
	         else 
	            if (sumOfDice == SEVEN) {// lose by rolling 7 before point
	               gameStatus = Status.LOST;
	               ++stepsTillLoseTotal;//输局步数+1
	             //如果大于20步，则全部储存在20下标处
	               ++stepsTillLoseFrequency[++stepsContinueSinglePlay<20 ? stepsContinueSinglePlay: 20];
	            }
	            else{
	            	++stepsContinueTotal;//继续游戏步数+1
	            	++stepsContinueSinglePlay;
	            }
	      } 
 
	      // display won or lost message
	      if (gameStatus == Status.WON) {
	         stepsTillWinTotal +=stepsContinueTotal; //如果最终胜利，continue状态的计数次数加入赢得游戏所需步数
	         stepsContinueTotal = 0; //单次游戏结束，continue状态的计数器置0
	         ++winTimes;
	      }
	      else {
	         stepsTillLoseTotal +=stepsContinueTotal; //如果最终输了，continue状态的计数次数算到输掉游戏所需步数
	         stepsContinueTotal = 0; //单次游戏结束，continue状态的计数器置0
	      }
	     
	   }
 
	   // roll dice, calculate sum and display results
	   public static int rollDice()
	   {
	      // pick random die values
	      int die1 = 1 + randomNumbers.nextInt(6); // first die roll
	      int die2 = 1 + randomNumbers.nextInt(6); // second die roll
	      int sum = die1 + die2; // sum of die values
	      return sum; 
	   }
	   
	   public static void initialize()
	   {
		 //全局变量清零
		   winTimes = 0; 
		   stepsTillWinTotal = 0; 
		   stepsTillLoseTotal = 0;
		   stepsContinueTotal = 0;
 
		   for (int i=0;i<21;i++){
			  stepsTillWinFrequency[i] = 0; 
			  stepsTillLoseFrequency[i] = 0;
		   }
	   }
	} 