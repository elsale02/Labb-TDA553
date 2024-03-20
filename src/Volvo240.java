import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Volvo240 extends Car {
    private final String imageAddress = "pics/Volvo240.jpg";
    public final static double trimFactor = 1.25;
    public Volvo240(){
        super(4,Color.black,100,"Volvo240");
        stopEngine();
    }
    @Override
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
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
