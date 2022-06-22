
public class Book extends Item{
	
	private String author;//作者
	
	public Book(String[] inputs) {
		super(inputs);
		this.author=inputs[3];
		this.setPublisher(inputs[4]);
	}

	//Getters Setters
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//重写书籍输出
	@Override
	public void printItem()
	{
		System.out.printf("%s，编号：%s ISBN：%s 书名：%s", getType(), getNumber(),
				getIsbn(), getName());
		System.out.printf("  作者：%s  出版社：%s  借阅状态：%s\n",  
				getAuthor(), getPublisher(),getStatus());
	}
}
