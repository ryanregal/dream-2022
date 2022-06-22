
public abstract class Item {
	private String number;//编号
	private String isbn;
	private String name;//名称
	private String status;//借阅状态
	private String publisher;//出版社
	private String type;//杂志还是书
	
	//构造函数
	public Item(String[] inputs)
	{
		this.type = inputs[0];
		this.isbn = inputs[1];
		this.name = inputs[2];
		this.status="未借出";
	}
	
	//打印书籍
	public abstract void printItem();
	
	//Getters Setters
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
