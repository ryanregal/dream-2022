//小汽车类
public class Car extends Vehicle{

	private String carriagesNumber;//车厢数 
	
	//构造函数
	public Car(String[] inputs) throws IllegalYearException {
		super(inputs);
		this.carriagesNumber = inputs[5];
	}

	//Getters Setters
	public String getCarriagesNumber() {
		return carriagesNumber;
	}
	public void setCarriagesNumber(String carriagesNumber) {
		this.carriagesNumber = carriagesNumber;
	}
	
	//重写小汽车输出
	@Override
	public void printVehicle()
	{
		System.out.printf("%s，品牌：%s 颜色：%s 出厂年份：%d", getCarType(), getTradeMark(),
				getColor(), getFactoryYear());
		System.out.printf(" 载客量：%.0f人  厢数：%s\n",  
				getCapacity(), carriagesNumber);
	}
	
}
