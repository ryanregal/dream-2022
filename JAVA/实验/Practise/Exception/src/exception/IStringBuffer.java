package exception;

public interface IStringBuffer {

	//追加字符串
    public void append(String str) throws IndexIsOutofRangeException, IndexIsNagetiveException; 
    //追加字符
    public void append(char c)  throws IndexIsOutofRangeException, IndexIsNagetiveException; 
    //指定位置插入字符
    public void insert(int pos,char b) throws IndexIsOutofRangeException, IndexIsNagetiveException; 
    //指定位置插入字符串
    public void insert(int pos,String b) throws IndexIsOutofRangeException, IndexIsNagetiveException; 
    //从开始位置删除剩下的
    public void delete(int start) throws IndexIsOutofRangeException, IndexIsNagetiveException; 
    //从开始位置删除结束位置-1
    public void delete(int start,int end) throws IndexIsOutofRangeException, IndexIsNagetiveException; 
    //反转
    public void reverse(); 
    //返回长度
    public int length();

}