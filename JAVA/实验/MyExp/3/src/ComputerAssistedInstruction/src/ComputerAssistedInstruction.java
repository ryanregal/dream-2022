//学习乘法程序1.0
import java.util.Scanner;
import java.util.Random;

public class ComputerAssistedInstruction{
	
    private static Random randomGenerator = new Random();//随机数生成器
    //main方法
    public static void main(String[] args){
        Scanner answer = new Scanner(System.in);
        int[] digits = new int[2];//存储两位要相乘的数
        char iscontinue = 'y';

        while(iscontinue == 'y'){//当用户输入y表示要继续做题
            digits = newQuestion();//用数组储存得到的新问题
            while(true){
            	//输出问题
                System.out.printf("How much is %d times %d? ", digits[0], digits[1]);
                if(checkAnswer(digits[0], digits[1], answer.nextInt())){
                    System.out.println("Very good!");//回答正确
                    break;
                }else{
                    System.out.println("No. Please try again.");//回答错误
                }
            }
            System.out.print("Continue? (y/n) ");
            iscontinue = answer.next().charAt(0);//返回索引0处的char值
        }
        answer.close();//关闭scanner
    }
    
    //使用一个单独的方法来生成每个新的问题
    public static int[] newQuestion(){
    	//随机生成两个正的一位数整数
        int[] tmp = {randomGenerator.nextInt(9), randomGenerator.nextInt(9)};
        return tmp;
    }
    
    // 检查答案
    public static boolean checkAnswer(int x, int y, int answer){
        return (answer == x * y) ? true : false;
    }
}