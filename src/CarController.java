import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements ActionListener, VehicleObserver {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    //private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    //private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame = new CarView("CarSim 1.0");
    //static CarController carC = new CarController();

    // A list of cars, modify if needed
    VehicleComposite cars;
    List<Building> buildings;

    public CarController(VehicleComposite vehicles, List<Building> buildings){
        cars = vehicles;
        this.buildings = buildings;
    }

    //methods:

        // Start the timer
    public void inputListeners() {
        //carC.timer.start();

        // Define what the buttons should do if they are pressed.
        frame.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                frame.gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });
        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gas(frame.gasAmount);
            }
        });
        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brake(frame.gasAmount);
            }
        });
        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turboOn();
            }
        });
        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turboOff();
            }
        });
        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liftBed();
            }
        });
        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerBed();
            }
        });
        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startEngine();
            }
        });
        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopEngine();
            }
        });
    }
    @Override
    public void update(int x, int y) {
        cars.move();
        frame.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        cars.move();
        frame.repaint();
    }
    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
/*
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // HEIGHT and WIDTH are constants based on the frame's height and width, but modified so that the
            // images stay completely inside the frame. 110 and 150 are experimentally found
            final int WIDTH = frame.getWidth() - 110;
            final int HEIGHT = frame.getHeight() - 150;

            // Pick-up range for the workshops
            final int RADIUS = 100;

            int workshopX = DrawPanel.getImageCoordinates(3)[0];
            int workshopY = DrawPanel.getImageCoordinates(3)[1];

            /*for (int i = 0; i < cars.size(); i++) {
                Vehicle car = cars.get(i);

                // Make sure the cars cannot go outside the frame
                // When hit wall: stop, turn around, then start again
                boolean isOutOfFrame = car.x >= WIDTH || car.x < 0 || car.y >= HEIGHT || car.y < 0;
                if(isOutOfFrame) {
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                    while(isOutOfFrame){
                        isOutOfFrame = car.x >= WIDTH || car.x < 0 || car.y >= HEIGHT || car.y < 0;
                        car.move();
                    }
                }
                
                // Interactions with workshop
                if(Math.abs(car.x - workshopX) < RADIUS && Math.abs(car.y - workshopY) < RADIUS) {
                    if(car instanceof Volvo240) {
                        volvoWorkshop.storeCar((Volvo240) car);
                        car.x = workshopX;
                        car.y = workshopY;
                        car.stopEngine();
                    }
                }
                
                // Move the car according to its currentSpeed
                car.move();
                int x = (int) Math.round(car.x);
                int y = (int) Math.round(car.y);
                frame.drawPanel.moveit(i, x, y);
                frame.drawPanel.repaint();
            }
        }
    }*/

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        cars.gas(gas);
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        cars.brake(brake);
    }
    void turboOn(){
        int max = cars.getCarCount();
        for(int i = 0; i < max; i++) {
            if(cars.getCar(i) instanceof Saab95){
                ((Saab95) cars.getCar(i)).setTurboOn();
            }
        }
    }
    void turboOff(){
        int max = cars.getCarCount();
        for(int i = 0; i < max; i++) {
            if(cars.getCar(i) instanceof Saab95){
                ((Saab95) cars.getCar(i)).setTurboOff();
            }
        }
    }
    void liftBed(){
        int max = cars.getCarCount();
        for(int i = 0; i < max; i++) {
            if(cars.getCar(i) instanceof Scania){
                ((Scania) cars.getCar(i)).raiseBed();
            }
        }
    }
    void lowerBed(){
        int max = cars.getCarCount();
        for(int i = 0; i < max; i++) {
            if(cars.getCar(i) instanceof Scania){
                ((Scania) cars.getCar(i)).lowerBed();
            }
        }
    }
    void startEngine() {
        cars.startEngine();
    }
    void stopEngine() {
        cars.stopEngine();
    }
}
