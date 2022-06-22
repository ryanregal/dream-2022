//判断每次掷骰子时应获得的奖励，判断是否已发放所有的奖励
package game;
 
public class BoCake
{
	//记录每个奖的名额，从下往上分别指状元、对堂、三红、四进、二举、一秀
	public static final int numA = 1,numB = 2,numC = 4,numD = 8,numE= 16,numF = 32;
	public int[] reward1=new int[7]; //记录状元
	public int reward2=0,reward3=0,reward4=0,reward5=0,reward6=0;//记录其他
	
	//初始化
	public BoCake(){
		for(int i=1;i<7;i++) {
			reward1[i]=0;
		}
		reward2=reward3=reward4=reward5=reward6=0;
	}
	
	
	//判断奖励
	public void Rewardjudge(Dice dice,Player player)
	{
		//a,b,c,d,e,f储存玩家一次扔的六个骰子点数（除了4之外）
		int four=0,a=0,b=0,c=0,d=0,e=0,f=0; 
		for(int i=1;i<7;i++) {
			if(dice.random[i]==4) four++;//记录点数为4的骰子数目
			else if(a==0) a=dice.random[i];
			else if(b==0) b=dice.random[i];
			else if(c==0) c=dice.random[i];
			else if(d==0) d=dice.random[i];
			else if(e==0) e=dice.random[i];
			else if(f==0) f=dice.random[i];
		}
		
		//判断奖励 状元等级
		
		//状元插金花>六博红>六博黑>五王>五子登科>状元
		//六博红:444444，存在reward的第2位
		if(four==6) {
			reward1[2]=1;  
			System.out.println("六博红，你进入了状元候选！");
		}
		//六博黑：XXXXXX(没有4出现)
		else if(four==0 && a==b && b==c && c==d && d==e && e==f) {
			reward1[3]=1;
			System.out.println("六博黑，你进入了状元候选！");
		}
		//五王：44444X
		else if(four==5) {//不同的五王比较：6>5>3>2>1
			if(a==6) reward1[4]=6;
			else if(a==5) reward1[4]=5;
			else if(a==3) reward1[4]=3;
			else if(a==2) reward1[4]=2;
			else if(a==1) reward1[4]=1;
			System.out.println("五王，你进入了状元候选！");
		}
		//五子登科:XXXXXA
		else if(a==b && b==c && c==d && d==e){
			if(four==1 && reward6<numF) {//当还留有一秀名额时，该玩家也同时获得一秀
				reward6++; 
				player.result[6]++;
			}
			//不同的五子登科比较大小6>5>3>2>1
			if(a==6) reward1[5]=6;
			else if(a==5) reward1[5]=5;
			else if(a==3) reward1[5]=3;
			else if(a==2) reward1[5]=2;
			else if(a==1) reward1[5]=1;
			System.out.println("五子登科，你进入了状元候选！");
		}
		//状元插金花：444411
		else if(four==4){
			if(a==1 && b==1) {
				reward1[1]=1;
				System.out.println("状元插金花，恭喜获得状元！");
			}
			else {
				reward1[6]=1;//普通状元
				System.out.println("状元，你进入了候选！");
			}
		}
		
		//榜眼：123456（全部的数不相等，且有一个four）
		else if(four==1 && a!=b && b!=c && c!=d && d!=e && a!=c && a!=d && a!=e && b!=d && b!=e && c!=e)
		{
			if(reward2<numB) {//当还留有对堂名额时
				reward2++; 
				player.result[2]++; 
				System.out.println("恭喜获得对堂！");
			}
			else{ 
				System.out.println("抱歉，对堂名额已经没有啦。");
			}
		}
			
		//探花：444XXX
		else if(four==3) 
		{
			if(reward3<numC){//当还留有三红名额时
				reward3++; player.result[3]++; 
				System.out.println("恭喜获得三红！");
			}
			else  { 
				System.out.println("抱歉，三红名额已经没有啦。");
			}
		}
		//进士：AAAAXX
		else if(a==b && b==c && c==d )
		{
			if(four==2)//还有两个4，代表二举
			{
				if(reward5<numE) {
					reward5++; player.result[5]++; 
					System.out.println("恭喜获得二举！");
				}
				else  { 
					System.out.println("抱歉，二举已经没有啦。");
				}
			}
			else if(four==1)//如果还有名额，可以获得一秀
			{
				if(reward6<numF) {
					reward6++; player.result[6]++; 
					System.out.println("恭喜获得一秀！");
				}
				else  { 
					System.out.println("抱歉，一秀已经没有啦。");
				}
			}
			if(reward4<numD) {
				reward4++; player.result[4]++; 
				System.out.println("恭喜获得四进！");
				}
			else  { 
				System.out.println("抱歉，四进已经没有啦。");
			}
		}
		//举人：44XXXX
		else if(four==2) 
		{
			if(reward5<numE) {
				reward5++; player.result[5]++; 
				System.out.println("恭喜获得二举！");
		}
			else { 
				System.out.println("抱歉，二举已经没有啦。");
			}
		}
		//秀才:4XXXXX
		else if(four==1) 
		{
			if(reward6<numF) {
				reward6++; player.result[6]++;
				System.out.println("恭喜获得一秀！");
			}
			else  { 
				System.out.println("抱歉，一秀已经没有啦。");
			}
		}
	}
 
//判断游戏结束
	public boolean isOver()
	{
		if(!(reward2==2 && reward3==4 && reward4==8 && reward5==16 && reward6==32) ) {
			return false;
		}
  	    for(int i=1;i<7;i++) {
		   if(reward1[i]!=0) {
			   return true;//包括状元的所有奖项都确定了
		   }
	    }
	    return false;	
	}
}