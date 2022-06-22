// Fig. 6.8: Craps.java
// Craps类模拟骰子游戏
import java.security.SecureRandom;

public class Craps {
   // 创建用于 rollDice 的安全随机数生成器
   private static final SecureRandom randomNumbers = new SecureRandom();

   // 表示游戏状态的常量的枚举类型
   private enum Status {CONTINUE, WON, LOST};                

   // 表示常见掷骰子结果的常数
   private static final int SNAKE_EYES = 2;
   private static final int TREY = 3;
   private static final int SEVEN = 7;     
   private static final int YO_LEVEN = 11; 
   private static final int BOX_CARS = 12;

   // 玩一局游戏
   public static void main(String[] args) {
      int myPoint = 0; // 第一轮的得分
      Status gameStatus; // can contain CONTINUE, WON or LOST

      int sumOfDice = rollDice(); // first roll of the dice

      // 根据第一次确定游戏状态和分数
      switch (sumOfDice) {
         //coming out roll
         case SEVEN: // win with 7 on first roll    
         case YO_LEVEN: // win with 11 on first roll
            gameStatus = Status.WON;
            break;
         case SNAKE_EYES: // lose with 2 on first roll
         case TREY: // lose with 3 on first roll      
         case BOX_CARS: // lose with 12 on first roll 
            gameStatus = Status.LOST;
            break;
         default: // did not win or lose, so remember point  
            gameStatus = Status.CONTINUE; // game is not over
            myPoint = sumOfDice; // remember the point       
            System.out.printf("Point is %d%n", myPoint);
            break; 
      }

      // while game is not complete
      while (gameStatus == Status.CONTINUE) { // not WON or LOST
         sumOfDice = rollDice(); // roll dice again

         // determine game status
         if (sumOfDice == myPoint) { // win by rolling the same number
            gameStatus = Status.WON;
         } 
         else { 
            if (sumOfDice == SEVEN) { // lose by rolling 7 before point
               gameStatus = Status.LOST;
            } 
         } 
      } 

      // display won or lost message
      if (gameStatus == Status.WON) {
         System.out.println("Player wins");
      } 
      else {
         System.out.println("Player loses");
      } 
   } 

   // roll dice, calculate sum and display results
   public static int rollDice() {
      // pick random die values
      int die1 = 1 + randomNumbers.nextInt(6); // first die roll
      int die2 = 1 + randomNumbers.nextInt(6); // second die roll

      int sum = die1 + die2; // sum of die values

      // display results of this roll
      System.out.printf("Player rolled %d + %d = %d%n", die1, die2, sum);

      return sum; 
   } 
} 

