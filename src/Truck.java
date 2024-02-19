import java.awt.*;

public abstract class Truck extends Vehicle implements HasBed {

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }
}
