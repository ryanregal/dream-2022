//载具类
public abstract class Vehicle {
	
	private String tradeMark;//商标  
	private String color;//颜色  
	private int factoryYear;//出厂年
	private String carType;//小汽车还是卡车
	private double capacity;	//小汽车载客量或卡车载重量

	//构造函数
	public Vehicle(String[] inputs) throws IllegalYearException
	{
		this.carType = inputs[0];
		this.tradeMark = inputs[1];
		this.color = inputs[2];
		setCapacity(Double.parseDouble(inputs[3]));
		setFactoryYear(Integer.parseInt(inputs[4]));
	}
	
	//Getters Setters
	public String getTradeMark() {
		return tradeMark;
	}
	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getFactoryYear() {
		return factoryYear;
	}
	public void setFactoryYear(int factoryYear) throws IllegalYearException {
		if(factoryYear<=0||factoryYear>2022) {
			System.out.println("年份应该大于0小于等于2022");
			throw new IllegalYearException("Year must be> 0 and <=2022!");	
		}
		else this.factoryYear = factoryYear;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		
		if(capacity<=0) {
			System.out.println("载具容量应该大于0");
			throw new IllegalArgumentException("Capacity must be >= 0.0f");
		}
		else this.capacity = capacity;
	}

	//打印载具
	public abstract void printVehicle();
	
}
