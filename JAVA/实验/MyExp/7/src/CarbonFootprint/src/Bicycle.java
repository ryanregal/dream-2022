
public class Bicycle implements CarbonFootprint{
    private final double CALORIES_PER_MILE = 34.0f;
    private double distance;

    // constructor
    public Bicycle(double distance){
        setDistance(distance);
    }
    
    // SETTERS GETTERS
    public void setDistance(double distance){
        if(distance >= 0.0f)
            this.distance = distance;
        else
            throw new IllegalArgumentException(
                    "distance must be >= 0.0f");
    }
    public double getDistance(){
        return this.distance;
    }
    
    // calculate carbon footprint
    @Override
    public double getCarbonFootprint(){
        return distance * CALORIES_PER_MILE;
    }
    
    // String representation of object
    @Override
    public String toString(){
        return String.format("Bicycle\nDistance %.2f miles\nCarbon Footprint = %.2f cal",
                getDistance(), getCarbonFootprint());
    }
}