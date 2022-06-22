public class PrimeNumbers{
    public static void main(String[] args){
        int count = 0;
        System.out.printf("小于10000的素数有：\n");
        for(int i=2; i<10000; i++){
            if(isPrimeRefined(i)){//当i是素数
                System.out.printf("%d ", i);
                count++;
            }
            //每十个输出一行
            if(count == 10){
                count = 0;
                System.out.println();
            }
        }
        System.out.println();
    }
   //方法一
    public static boolean isPrime(int n){
    	if(n<2) return false; //最小的素数是2
        for(int i=2; i<n/2+1; i++){
            if(n % i == 0)  return false;
        }
        return true;
    }
    //方法二
    public static boolean isPrimeRefined(int n){
    	if(n<2) return false; //最小的素数是2
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i == 0)  return false;
        }
        return true;
    }
}
