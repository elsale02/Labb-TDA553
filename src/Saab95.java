import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Saab95 extends Car {
    private final String imageAddress = "pics/Saab95.jpg";
    private boolean turboOn;
    public Saab95(){
        super(2,Color.red,125,"Saab95");
        this.turboOn = false;
        stopEngine();
    }
    public void setTurboOn(){
	    turboOn = true;
    }
    public void setTurboOff(){
	    turboOn = false;
    }
    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
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
