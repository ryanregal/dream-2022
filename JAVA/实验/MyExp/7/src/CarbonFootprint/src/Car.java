
public class Car implements CarbonFootprint{
    private final double KG_PER_LITRE_PETROL = 2.39;//每升汽油的二氧化碳排放
    private final double KG_PER_LITRE_DIESEL = 2.62;//每升柴油的二氧化碳排放
    private final double LITRES_IN_US_GALLON = 3.785;//换算成加仑

    public enum FuelUnit {LITRES, GALLONS};//汽油单位
    public enum FuelType {DIESEL, PETROL};//汽油类型
    public enum DistanceUnit {MILES, KILOMETRES};//距离单位
    public enum CalculateBy {FUEL_USED, MILEAGE};//计算方式

    private FuelUnit fuelUnit;
    private FuelType fuelType;
    private DistanceUnit distanceUnit;
    private CalculateBy calculateBy;

    private double fuelAmount;
    private double distance;
    private double mpg;

    // constructor
    // fuel used
    public Car(FuelUnit fuelUnit, FuelType fuelType, double fuelAmount){
        setFuelUnit(fuelUnit);
        setFuelType(fuelType);
        setFuelAmount(fuelAmount);
        setCalculateBy(CalculateBy.FUEL_USED);
    }
    // mileage
    public Car(DistanceUnit distanceUnit, double distance, double mpg, FuelType fuelType){
        setDistanceUnit(distanceUnit);
        setDistance(distance);
        setConsumption(mpg);
        setFuelType(fuelType);
        setCalculateBy(CalculateBy.MILEAGE);
    }
    
    // SETTERS GETTERS
    public void setFuelUnit(FuelUnit fuelUnit){
        this.fuelUnit = fuelUnit;
    }
    public void setFuelType(FuelType fuelType){
        this.fuelType = fuelType;
    }
    public void setDistanceUnit(DistanceUnit distanceUnit){
        this.distanceUnit = distanceUnit;
    }
    public void setCalculateBy(CalculateBy calculateBy){
        this.calculateBy = calculateBy;
    }
    
    public void setFuelAmount(double fuelAmount){
        if(fuelAmount >= 0.0f)
            this.fuelAmount = fuelAmount;
        else
            throw new IllegalArgumentException(
                    "fuelAmount must be >= 0.0f");
    }
    public void setDistance(double distance){
        if(distance >= 0.0f)
            this.distance = distance;
        else
            throw new IllegalArgumentException(
                    "distance must be >= 0.0f");
    }
    public void setConsumption(double mpg){
        if(mpg >= 0.0f)
            this.mpg = mpg;
        else
            throw new IllegalArgumentException(
                    "mpg must be >= 0.0f");
    }

    public String getFuelUnit(){
        switch(this.fuelUnit){
            case LITRES:
                return "litres";
            case GALLONS:
                return "gallons";
            default:
                throw new IllegalArgumentException(
                        "fuelUnit must be LITRES or GALLONS");
        }
    }
    public String getFuelType(){
        switch(this.fuelType){
            case DIESEL:
                return "diesel";
            case PETROL:
                return "petrol";
            default:
                throw new IllegalArgumentException(
                        "FuelType must be DIESEL or PETROL");
        }
    }
    public double getFuelAmount(){
        return this.fuelAmount;
    }
    public String getDistanceUnit(){
        switch(this.distanceUnit){
            case MILES:
                return "miles";
            case KILOMETRES:
                return "kilometres";
            default:
                throw new IllegalArgumentException(
                        "distanceUnit must be MILES or KILOMETRES");
        }
    }
    public double getDistance(){
        return this.distance;
    }
    public double getConsumption(){
        return this.mpg;
    }
    public String getCalculateBy(){
        switch(this.calculateBy){
            case FUEL_USED:
                return "CO2 Emmissions calcualted by Fuel Used";
            case MILEAGE:
                return "CO2 Emmissions calculated by Distance";
            default:
                throw new IllegalArgumentException(
                        "calculateBy must be FUEL_USED or MILEAGE");
        }
    }
    public String getParameters(){
        switch(this.calculateBy){
            case FUEL_USED:
                return String.format("%s\n%.2f %s %s %s",
                        getCalculateBy(),
                        getFuelAmount(), getFuelUnit(), "of", getFuelType());
            case MILEAGE:
                return String.format("%s\n%s %.2f %s\n%s %s",
                        getCalculateBy(),
                        "Distance", getDistance(), getDistanceUnit(),
                        "Fuel Type", getFuelType());
            default:
                throw new IllegalArgumentException(
                        "calculateBy must be FUEL_USED or MILEAGE");
        }
    }
    
    // calculate carbon footprint
    @Override
    public double getCarbonFootprint(){
        switch(this.calculateBy){
            case FUEL_USED:
                switch(this.fuelUnit){
                    case LITRES:
                        if(this.fuelType == FuelType.PETROL)
                            return KG_PER_LITRE_PETROL * getFuelAmount();
                        else
                            return KG_PER_LITRE_DIESEL * getFuelAmount();
                    case GALLONS:
                        if(this.fuelType == FuelType.PETROL)
                            return (KG_PER_LITRE_PETROL * LITRES_IN_US_GALLON) * getFuelAmount();
                        else
                            return (KG_PER_LITRE_DIESEL * LITRES_IN_US_GALLON) * getFuelAmount();
                    default:
                        throw new IllegalArgumentException("cannot calculate due to invalid fuelUnit or fuelType");
                }
            case MILEAGE:
                if(this.fuelType == FuelType.PETROL)
                    return ((KG_PER_LITRE_PETROL * LITRES_IN_US_GALLON) / getConsumption()) *getDistance();
                else
                    return ((KG_PER_LITRE_DIESEL * LITRES_IN_US_GALLON) / getConsumption()) *getDistance();
            default:
                throw new IllegalArgumentException(
                    "cannot calculate due to invalid fuelType or calculateBy");
        }
    }
    
    // String representation of object
    @Override
    public String toString(){
        return String.format("%s\n%s\n%s = %.2f kg\n","Car", getParameters(),
                "CO2 produced", getCarbonFootprint());
    }
}