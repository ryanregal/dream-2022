public class Test{
    public static void main(String[] args){
        Time2Enhanced t1 = new Time2Enhanced();             // 00:00:00
        Time2Enhanced t2 = new Time2Enhanced(2);            // 02:00:00
        Time2Enhanced t3 = new Time2Enhanced(21, 34);       // 21:34:00
        Time2Enhanced t4 = new Time2Enhanced(12, 25, 42);   // 12:25:42
        Time2Enhanced t5 = new Time2Enhanced(t4);           // 12:25:42
        Time2Enhanced t7 = new Time2Enhanced(22, 58, 59);   // 22:58:59
        System.out.println("test1:");
        System.out.printf("%s\n", t1.toUniversalString());
        System.out.printf("%s\n", t1.toString());
        System.out.println("\ntest2:");
        System.out.printf("%s\n", t2.toUniversalString());
        System.out.printf("%s\n", t2.toString());
        System.out.println("\ntest3:");
        System.out.printf("%s\n", t3.toUniversalString());
        System.out.printf("%s\n", t3.toString());
        System.out.println("\ntest4:");
        System.out.printf("%s\n", t4.toUniversalString());
        System.out.printf("%s\n", t4.toString());
        System.out.println("\ntest5: ");
        System.out.printf("%s\n", t5.toUniversalString());
        System.out.printf("%s\n", t5.toString());
        System.out.println("\ntest7:");
        System.out.printf("%s\n", t7.toUniversalString());
        System.out.printf("%s\n", t7.toString());
        System.out.println("\ntest8: test7 increment hour(1)");
        t7.incrementHour();
        System.out.printf("%s\n", t7.toUniversalString());
        System.out.printf("%s\n", t7.toString());
        System.out.println("\ntest9: test8 increment minute(2)");
        t7.incrementMinute();t7.incrementMinute();
        System.out.printf("%s\n", t7.toUniversalString());
        System.out.printf("%s\n", t7.toString());
        // 尝试使用无效值初始化test6,抛出报错
        try{
            Time2Enhanced test10 = new Time2Enhanced(27, 74, 99);
        }
        catch(IllegalArgumentException e){
            System.out.printf("\nException while initialising test10: %s\n",e.getMessage());
        }
    }
}