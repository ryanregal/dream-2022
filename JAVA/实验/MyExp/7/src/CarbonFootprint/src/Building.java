
public class Building implements CarbonFootprint{

	private int height;//建筑高度
	private double area;//建筑占地面积，平方米
	private final double KG_PRE_SQUARE_METER = 32.3;
	
	//Constructor
	public Building(int height, double area) {
		this.height = height;
		this.area = area;
	}
	
	//Getters Setters
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}

	//toString方法
	@Override
	public String toString() {
		return String.format("Building\nArea %.2f m*m\nCO2 produced = %.2f kg\n", area,getCarbonFootprint());
	}
	
	@Override
	public double getCarbonFootprint() {
		return area*KG_PRE_SQUARE_METER;
	}
}