package game;
//玩家类，记录玩家获得的每种奖励的个数
public class Player 
{
	//记录每个奖的名额，从下往上分别指状元、对堂、三红、四进、二举、一秀
	public static final int A = 1,B = 2,C = 4,D = 8,E= 16,F = 32;
	public int id; //玩家号码
	public int[] result=new int[7]; //记录玩家结果
	
	public Player()
	{
		id=0;
		for(int i=1;i<7;i++) {
			result[i]=0;
		}
	}
	
//判断玩家所获奖励
	public void winRecord(Dice dice)
	{
		int a=0,b=0,c=0,d=0,e=0,f=0,four=0; 
		//a,b,c,d,e,f储存玩家一次扔的六个骰子点数（除了4之外）
		for(int i=1;i<7;i++) 
		{
			if(dice.random[i]==4) four++; //记录点数为4的骰子数目
			else if(a==0) a=dice.random[i];
			else if(b==0) b=dice.random[i];
			else if(c==0) c=dice.random[i];
			else if(d==0) d=dice.random[i];
			else if(e==0) e=dice.random[i];
			else if(f==0) f=dice.random[i];
		}
		
		//判断奖励 状元等级
		
		//状元插金花：444411
		if(four==4 && a==1 && b==1 && (result[1]>1||result[1]==0)) result[1]=1;
		//六博红：444444
		if(four==6 && (result[1]>2||result[1]==0)) result[1]=2;  
		//六博黑：XXXXXX(没有4出现)
		else if(four==0 && a==b && b==c && c==d && d==e && e==f && (result[1]>3||result[1]==0)) 
			result[1]=3;
		//五王：44444X
		else if(four==5) 
		{//不同的五王比较：6>5>3>2>1
			if(a==6 && (result[1]>4||result[1]==0)) result[1]=4;
			else if(a==5 && (result[1]>5||result[1]==0)) result[1]=5;
			else if(a==3 && (result[1]>6||result[1]==0)) result[1]=6;
			else if(a==2 && (result[1]>7||result[1]==0)) result[1]=7;
			else if(a==1 && (result[1]>8||result[1]==0)) result[1]=8;
		}
		//五子登科:XXXXXA
		else if(a==b && b==c && c==d && d==e)
		{//不同的五子登科比较大小6>5>3>2>1
			if(a==6 && (result[1]>9||result[1]==0)) result[1]=9;
			else if(a==5 && (result[1]>10||result[1]==0)) result[1]=10;
			else if(a==3 && (result[1]>11||result[1]==0)) result[1]=11;
			else if(a==2 && (result[1]>12||result[1]==0)) result[1]=12;
			else if(a==1 && (result[1]>13||result[1]==0)) result[1]=13;
		}
		//状元4444XX
		else if(four==4 && result[1]==0)
		{
			result[1]=14;
		}
	}
}