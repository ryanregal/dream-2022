//非法地址异常

@SuppressWarnings("serial")
class IllegalAddressException extends Exception
{
    public IllegalAddressException(String message)
    {
        super(message);
    }
    public IllegalAddressException()
    {
        super();
    }
}