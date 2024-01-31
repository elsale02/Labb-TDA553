//Todo gör minst ett JUnit-test

import java.awt.*;

public class Scania extends Car implements HasBed{
    protected final Bed bed;
    public Scania() {
        super(2, Color.red, 230, "Scania R 730" );
        bed = new Bed();
        stopEngine();
    }

    @Override
    public void raiseBed() {                                 // Kollar så att flaket är sänkt, samt att lastbilen står stilla -> sen höjer
        if (currentSpeed == 0 && bed.getAngle() < 70) {
            bed.setAngle(bed.getAngle() + 10);
        }
    }

    @Override
    public void lowerBed() {
        if (currentSpeed == 0 && bed.getAngle() > 0) {
            bed.setAngle(bed.getAngle() - 10);
        }
    }

    @Override
    public void startEngine() {
        if (bed.getAngle() == 0) {
            super.startEngine();
        }
    }

    @Override
    public double speedFactor(){
        double trailerFactor = 0.7;
        return enginePower * 0.01 * trailerFactor;
    }
}
