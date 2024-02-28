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

public class DrawPanel extends JPanel /*implements VehicleObserver*/ {
    BufferedImage[] images = new BufferedImage[4];
    /*static Point[] points = new Point[] {new Point(0,0),
                                  new Point(0,100),
                                  new Point(0,200),
                                  new Point(300,300)};*/
    //List<BufferedImage> images = new ArrayList<>();
    private final static List<Point> points = new ArrayList<>();

    void moveit(int i, int x, int y){
        if( points.size() <= i){
            points.add(new Point(x,y));
        }
        points.get(i).x = x;
        points.get(i).y = y;
    }

    public static void addPoint(int x, int y) {
        points.add(new Point(x,y));
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        points.add(new Point(0,0));
        points.add(new Point(0,100));
        points.add(new Point(0,200));
        points.add(new Point(300,300));

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
            images[0] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            images[1] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            images[2] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            images[3] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < points.size(); i++) {

            if(images.length <= i){
                g.drawImage(images[0], points.get(i).x, points.get(i).y, null);
            }else {
                g.drawImage(images[i], points.get(i).x, points.get(i).y, null);
            }
        }
    }
}
