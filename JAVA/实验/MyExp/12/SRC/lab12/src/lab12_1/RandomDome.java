package lab12_1;
import java.util.Random;

public class RandomDome { 
	
	 public static void main(String[] args) {
	  // TODO Auto-generated method stub
	  int[]date=new int[20];
	  int sum=0;
	  Random random=new Random();
	  for (int i = 0; i < 1000; i++) {
		  date[random.nextInt(20)]++;
	  }
	  for (int j = 0; j <20; j++) {
	     System.out.println(j+1+"出现次数"+date[j]);
	     sum+=(j+1)*date[j];
	  }
	  System.out.println("\n平均值:"+sum/1000.0);
	}
}
