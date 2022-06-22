//非法姓名异常

@SuppressWarnings("serial")
class IllegaNameException extends Exception
{
   public IllegaNameException(String message)
   {
       super(message);
   }
   public IllegaNameException()
   {
       super();
   }
}