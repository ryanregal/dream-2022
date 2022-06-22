//自定义异常的定义、拋出和捕获试验
//自定义两个异常类非法姓名异常IllegaNameException和非法地址异常IllegalAddressException。
//定义Student类包含Name和Address属性，和setName、setAddress方法
//当姓名长度小于1或者大于5抛出IllegaNameExceptio
//当地址中不含有”省”或者“市”关键字抛出IllegalAddressException。
//在main方法中进行捕获试验。

public class Test{

        public static void main(String args[]) throws IllegaNameException, IllegalAddressException {
            Student s=new Student();
            try {
                s.setName("乔瑟夫乔斯达");
            }
           catch(IllegaNameException e)
           {
               System.out.println("\n姓名输入错误");
               e.printStackTrace();
           }
           
            try {
                s.setAddress("罗马斗兽场");
            }
            catch(IllegalAddressException e)
            {
                System.out.println("\n地址输入错误");
                e.printStackTrace();
            }
        }
}
