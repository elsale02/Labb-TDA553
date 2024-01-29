import java.awt.*;
import java.util.ArrayList;
public abstract class Truck extends Car{
    private final ArrayList<Car> carLoad;
    public Truck(int nrDoors, Color color, double enginePower, String modelName, int capacity){
        super(nrDoors,color,enginePower,modelName);
        this.carLoad = new ArrayList<Car>(capacity);

    }



















}
