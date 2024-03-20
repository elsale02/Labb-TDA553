import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Scania extends Truck {
    private final String imageAddress ="pics/Scania.jpg";
    private int angle;
    public Scania() {
        super(2, Color.red, 230, "Scania R 730");
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
    @Override
    public BufferedImage getImage(){
        try{
            return ImageIO.read(DrawPanel.class.getResourceAsStream(imageAddress));
        } catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
