import java.util.ArrayList;

public class CarbonFootprintTest{
    public static void main(String[] args){
        ArrayList<CarbonFootprint> cf = new ArrayList<CarbonFootprint>();

        cf.add(new Building(3,100));
        cf.add(new Car(Car.FuelUnit.GALLONS, Car.FuelType.PETROL, 20.5f));
        cf.add(new Car(Car.FuelUnit.LITRES, Car.FuelType.DIESEL, 55.0f));
        cf.add(new Car(Car.DistanceUnit.MILES, 10.0f, 10.0f, Car.FuelType.PETROL));
        cf.add(new Car(Car.DistanceUnit.KILOMETRES, 25, 50, Car.FuelType.DIESEL));
        cf.add(new Bicycle(10));

        for(CarbonFootprint footprint : cf){
            System.out.println(footprint.toString());
        }
    }
}

