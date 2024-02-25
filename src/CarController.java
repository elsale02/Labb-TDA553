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

public class CarController implements VehicleObserver {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    private final CarView frame = new CarView("CarSim 1.0");
    // HEIGHT and WIDTH are constants based on the frame's height and width, but modified so that the
    // images stay completely inside the frame. 110 and 150 are experimentally found
    private final int WIDTH = frame.getWidth() - 110;
    private final int HEIGHT = frame.getHeight() - 150;

    // Pick-up range for the workshops
    private final int RADIUS = 100;
    //static CarController carC = new CarController();

    // A list of cars, modify if needed
    VehicleComposite cars;
    List<Building> buildings;

    public CarController(VehicleComposite vehicles, List<Building> buildings){
        cars = vehicles;
        this.buildings = buildings;
    }

    //methods:

    public void inputListeners() {
        // Start the timer
        timer.start();

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
    public void update() {
        for (int i = 0; i < cars.getCarCount(); i++) {
            Vehicle car = cars.getCar(i);

            // Make sure the cars cannot go outside the frame
            // When hit wall: stop, turn around, then start again
            boolean isOutOfFrame = car.x >= WIDTH || car.x < 0 || car.y >= HEIGHT || car.y < 0;
            if (isOutOfFrame) {
                car.turnRight();
                car.turnRight();
            }

            // Interactions with workshop
            if (car instanceof Volvo240) {
                for (Building building : buildings) {
                    if (building instanceof AutoRepairShop<?>) {
                        if (Math.abs(car.x - building.getX()) < RADIUS && Math.abs(car.y - building.getY()) < RADIUS) {
                            ((AutoRepairShop<Volvo240>) building).storeCar((Volvo240) car);
                            car.x = building.getX();
                            car.y = building.getY();
                            car.stopEngine();
                        }
                    }
                }
            }

            // Update graphics positions
            frame.drawPanel.moveit(i, car.x, car.y);
        }
        frame.repaint();
    }
    private class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            cars.move();
        }
    }

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
