
public class Magazine extends Item{
	
	public Magazine(String[] inputs) {
		super(inputs);
		this.year=Integer.parseInt(inputs[3]);
		this.issueNumber=inputs[4];
		this.setPublisher(inputs[5]);
	}

	private int year;//出版年
	private String issueNumber;//出版期号
	
	//Getters Setters
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	
	//重写杂志输出
	@Override
	public void printItem()
	{
		System.out.printf("%s，编号：%s ISBN：%s 杂志名：%s", getType(), getNumber(),
				getIsbn(), getName());
		System.out.printf("  出版年：%s  期号：%s  出版社：%s  借阅状态：%s\n",  
				getYear(), getIssueNumber(),getPublisher(),getStatus());
	}
	
}
