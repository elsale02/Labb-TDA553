import java.awt.*;

public class Volvo240 extends Car{

    public final static double trimFactor = 1.25;
    private double currentSpeed; // The current speed of the car
    
    public Volvo240(){
        super(4,Color.black,100,"Volvo240");
        stopEngine();
    }

    @Override
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}
