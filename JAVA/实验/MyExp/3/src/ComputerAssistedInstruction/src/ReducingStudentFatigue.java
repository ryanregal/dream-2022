//学习乘法程序2.0
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class ReducingStudentFatigue{
    private static Random randomGenerator = new Random();//随机数生成器
    //储存答案正确与错误时可能打印的反馈
    private static ArrayList<String> success = new ArrayList<String>();
    private static ArrayList<String> failure = new ArrayList<String>();
    //main方法
    public static void main(String[] args){
        Scanner answer = new Scanner(System.in);
        int[] digits = new int[2];//存储两位要相乘的数
        char iscontinue = 'y';
        success.add("Very Good!");
        success.add("Excellent!");
        success.add("Nice Work!");
        success.add("Keep up the good work!");
        failure.add("No. Please try again.");
        failure.add("Wrong. Try once more.");
        failure.add("Don't give up.");
        failure.add("No. Keep trying.");

        while(iscontinue == 'y'){//当用户输入y表示要继续做题
            digits = newQuestion();//用数组储存得到的新问题
            while(true){
            	//输出问题
                System.out.printf("How much is %d times %d? ", digits[0], digits[1]);
                if(checkAnswer(digits[0], digits[1], answer.nextInt())){
                    printResponse("success");//回答正确，随机输出表扬
                    break;
                }
                else  printResponse("failure");//回答错误，随机输出安慰
                
            }
            System.out.print("Continue? (y/n) ");
            iscontinue= answer.next().charAt(0);//返回索引0处的char值
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
    // 从数组随机选择回复并打印到控制台
    public static void printResponse(String condition){
        switch(condition){
            case "success":
                System.out.println(success.get(randomGenerator.nextInt(success.size())));
                break;
            case "failure":
                System.out.println(failure.get(randomGenerator.nextInt(failure.size())));
                break;
            default:
                break;
        }
    }
}