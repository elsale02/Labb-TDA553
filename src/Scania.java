//Todo gör minst ett JUnit-test

public class Scania extends Car{

    private boolean hasTrailer;
    private double trailerAngle;

    public Scania() {
        super(2, color.red, 230, "Scania R 730" );
        this.hasTrailer = false;
        this.trailerAngle = 0;    //Flaket är i normalläge (sänkt).
        stopEngine();
    }

    public void raiseTrailer() {                                 // Kollar så att flaket är sänkt, samt att lastbilen står stilla -> sen höjer
        if (currentSpeed == 0 && trailerAngle == 0) {
            trailerAngle = 70;
        }
    }

    public void lowerTrailer() {
        if (currentSpeed == 0 && trailerAngle == 70) {
            trailerAngle = 0;
        }
    }

    @Override
    public void startEngine() {
        if (trailerAngle == 0) {
            super.startEngine();
        } else {
            System.out.println("Lower trailer before starting engine.");
        }
    }

    public void attachTrailer() { hasTrailer = true; }
    public void detachTrailer() { hasTrailer = false; }

    @Override
    public double speedFactor(){
        double trailerFactor = 1.0;
        if (hasTrailer) trailerFactor = 0.7;

        return enginePower * 0.01 * trailerFactor;
    }
}
