//卡车类
public class Truck extends Vehicle{
	
	//构造函数
	public Truck(String[] inputs) {
		super(inputs);
	}

	//重写卡车输出
	@Override
	public void printVehicle()
	{
		System.out.printf("%s，品牌：%s 颜色：%s 出厂年份：%d", getCarType(), getTradeMark(),
				getColor(), getFactoryYear());
		System.out.printf(" 载货量：%.2f吨\n", getCapacity());
	}
	
}
