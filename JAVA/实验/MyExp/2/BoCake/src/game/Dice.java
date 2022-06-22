//骰子类，实现随即掷骰子的功能
package game;
 
public class Dice {
	public int[] random=new int[7];  //储存玩家一次扔的六个骰子点数
	public Dice(){
		for(int i = 1;i<7;i++)
			random[i] =0;	
	}
	
//随机扔骰子
	public void rollDice(){
		for(int i = 1;i<7;i++) {
			random[i] = 1 + ( int )(Math.random() * 6);//函数返回一个浮点数, 伪随机数在范围从0到小于1
		}
	}	
 
//测试接口，输出随机数生成的骰子数列
	public void printDice(){
		System.out.println(" 你的骰子数列为：");
		for(int i = 1;i<7;i++){
			System.out.printf("%d  ",random[i]);
		}
		System.out.print("\n");
	}
}