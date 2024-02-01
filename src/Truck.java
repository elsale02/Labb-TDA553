import java.awt.*;

public abstract class Truck extends Vehicle implements HasBed {
    protected final Bed bed;
    public Truck(int nrDoors, Color color, double enginePower, String modelName, Bed bed) {
        super(nrDoors, color, enginePower, modelName);
        this.bed = bed;
    }
}
