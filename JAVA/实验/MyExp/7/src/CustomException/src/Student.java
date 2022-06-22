class Student
{
     String name;
     String address;

    public void setName(String name) throws IllegaNameException
    {
        int n=name.length();
        if(n<1||n>5)
        {
            throw new IllegaNameException("姓名长度不符合要求");
        }
        this.name = name;
    }

    public void setAddress(String address) throws  IllegalAddressException
    {
    	if(!(address.contains("省") || address.contains("市")))
        {
    		throw new IllegalAddressException("地址不符合要求，需包含省市");
        }
    	this.address = address;
    }
}