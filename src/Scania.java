import java.awt.*;

public class Scania extends Truck implements HasBed {
    private int angle;
    public Scania() {
        super(2, Color.red, 230, "Scania R 730", new Bed());
        stopEngine();
    }
    @Override
    public void raiseBed() {
        if (currentSpeed == 0 && getAngle() < 70) {
            setAngle(getAngle() + 10);
        }
    }
    public void setAngle(int angle) {
        this.angle = angle;
    }
    public int getAngle() {
        return this.angle;
    }
    @Override
    public void lowerBed() {
        if (currentSpeed == 0 && getAngle() > 0) {
            setAngle(getAngle() - 10);
        }
    }
    @Override
    public void startEngine() {
        if (getAngle() == 0) {
            super.startEngine();
        }
    }
    @Override
    public double speedFactor(){
        double trailerFactor = 0.7;
        return enginePower * 0.01 * trailerFactor;
    }
}
