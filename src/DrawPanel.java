import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Random;
// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    BufferedImage[] vehicleImages = new BufferedImage[3];
    BufferedImage buildingImage;
    //public final static List<Point> vehiclePoints = new ArrayList<>();
     static Point buildingPoint;

    void moveit(int i, int x, int y){
        VehicleComposite.carList.get(i).x = x;
        VehicleComposite.carList.get(i).y = y;
        //vehiclePoints.get(i).x = x;
        //vehiclePoints.get(i).y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            buildingImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.drawImage(buildingImage, buildingPoint.x, buildingPoint.y, null);
        for(int i = 0; i < VehicleComposite.carList.size(); i++) {

            if(vehicleImages.length <= i){
                g.drawImage(VehicleComposite.carList.get(i).getImage(), VehicleComposite.carList.get(i).x, VehicleComposite.carList.get(i).y, null);
            } else {
                g.drawImage(VehicleComposite.carList.get(i).getImage(), VehicleComposite.carList.get(i).x, VehicleComposite.carList.get(i).y, null);
            }
        }
    }

}
