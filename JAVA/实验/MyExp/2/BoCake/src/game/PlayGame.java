package game;
import java.util.Scanner;
 
public class PlayGame {
		//主函数
		public static void main(String[] args) {
			//指定用户数量
			System.out.println("请输入玩家数（范围6-10）:");
			Scanner scanner= new Scanner(System.in);
			int n=scanner.nextInt();
			System.out.printf("你输入了%d个玩家\n\n",n);
			System.out.printf("\n----------------游戏开始--------------------\n\n");
			n++;
			
			//扔骰子
			Dice dice=new Dice() ;
			BoCake cake = new BoCake();
			Player[] player=new Player[n];//建立一个大小为n的数组player，储存玩家
			for(int i=1;i<n;i++){
				player[i]=new Player();//初始化
				player[i].id=i;//输入每个玩家的id
			}
			
			//游戏过程
			while(!cake.isOver()) {
				for(int i=1;i<n;i++){
					System.out.printf("第%d号玩家：",i);
					dice.rollDice();//掷骰子，投掷的结果存在random中
					dice.printDice();//输出掷出的点数
					player[i].winRecord(dice);//记录玩家i所赢得的奖项
					cake.Rewardjudge(dice,player[i]);
					System.out.printf("\n");
					if(cake.isOver()) {
						break;
					}
				}
				System.out.printf("\n----------------此局结束--------------------\n\n");
			}
			
			System.out.printf("\n\n----------------游戏结果--------------------\n\n");
			
			//找出状元属于哪一种
			int win=0;
			if(cake.reward1[1]!=0) win=1;
			else if(cake.reward1[2]!=0) win=2;
			else if(cake.reward1[3]!=0) win=3;
			else if(cake.reward1[4]!=0)
			{
				if(cake.reward1[4]==6) win=4;
				else if(cake.reward1[4]==5) win=5;
				else if(cake.reward1[4]==3) win=6;
				else if(cake.reward1[4]==2) win=7;
				else if(cake.reward1[4]==1) win=8;
			}
			else if(cake.reward1[5]!=0)
			{
				if(cake.reward1[5]==6) win=9;
				else if(cake.reward1[5]==5) win=10;
				else if(cake.reward1[5]==3) win=11;
				else if(cake.reward1[5]==2) win=12;
				else if(cake.reward1[5]==1) win=13;
			}
			else if(cake.reward1[6]!=0) win=14;
			
			//再找出状元是哪一个人
			for(int i=1;i<n;i++) {
				if(player[i].result[1]==win) {
					player[i].result[1]=-1;
					System.out.printf("恭喜第%d号玩家获得状元！\n",i);
					break;
				}
			}
			//输出最终结果
			for(int i=1;i<n;i++)
			{
				System.out.printf("第%d号玩家，你获得了：",i);
				System.out.printf("状元%d个；",player[i].result[1]==-1?1:0);
				System.out.printf("对堂%d个；",player[i].result[2]);
				System.out.printf("三红%d个；",player[i].result[3]);
				System.out.printf("四进%d个；",player[i].result[4]);
				System.out.printf("二举%d个；",player[i].result[5]);
				System.out.printf("一秀%d个\n",player[i].result[6]);	
			}
			scanner.close();
			
		}
}