//学习乘法程序3.0
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class MonitoringStudentPerformance{
    private Random randomGenerator = new Random();//随机数生成器
  //储存答案正确与错误时可能打印的反馈
    private ArrayList<String> success = new ArrayList<String>();
    private ArrayList<String> failure = new ArrayList<String>();

    private int[] values = new int[2];//存储两位要相乘的数
    private int score = 0;//储存一轮的分数
    
    //main方法
    public static void main(String[] args){
        Scanner answer = new Scanner(System.in);
        MonitoringStudentPerformance round = new MonitoringStudentPerformance();
        int count = 0;
        char iscontinue = 'y';
        while(iscontinue == 'y'){
            //一轮问题为10个
            while(count != 10){
            	round.getQuestion();//打印问题
            	round.checkAnswer(answer.nextInt());
                ++count;
            }
            round.getResults();//打印结果
            System.out.print("Continue? (y/n) ");
            iscontinue = answer.next().charAt(0);//返回索引0处的char值
            if(iscontinue == 'y') {
            	count=0;//已回答问题个数重置回0
            	round.reset();
            }
        }
        answer.close();//关闭scanner   
    }
    // 构造函数
    public MonitoringStudentPerformance(){
        success.add("Very Good!");
        success.add("Excellent!");
        success.add("Nice Work!");
        success.add("Keep up the good work!");
        failure.add("No. Please try again.");
        failure.add("Wrong. Try once more.");
        failure.add("Don't give up.");
        failure.add("No. Keep trying.");
        setQuestion(); // 生成新的问题的两个乘数
    }
    // 生成新的问题的两个乘数
    private void setQuestion(){
        values[0] = randomGenerator.nextInt(9);
        values[1] = randomGenerator.nextInt(9);
    }
    // 打印问题
    public void getQuestion(){
        System.out.printf("How much is %d times %d? ", values[0], values[1]);
    }
    //打印结果
    public void getResults(){
        System.out.printf("You scored %d out of 10.\n", score);
        if(score >= (10 * 0.75)){
            System.out.println("Congratulations you are ready to go the next level!");
        }else{
            System.out.println("Please ask your teacher for extra help.");
        }
    }
    //检查答案，随机生成反应
    public void checkAnswer(int answer){
        if(answer == values[0] * values[1]){
            System.out.println(success.get(randomGenerator.nextInt(success.size())));
            setQuestion();//如果正确，生成新问题
            ++score;//分数加1
        }
        else System.out.println(failure.get(randomGenerator.nextInt(failure.size())));
    }
    // 重置分数，并开始新的一轮
    public void reset(){
    	score = 0;
        setQuestion();
    }
}