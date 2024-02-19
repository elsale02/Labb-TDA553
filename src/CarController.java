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

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    static CarView frame = new CarView("CarSim 1.0");
    static CarController carC = new CarController();

    // A list of cars, modify if needed
    List<Vehicle> cars = new ArrayList<>();
    AutoRepairShop<Volvo240> volvoWorkshop = new AutoRepairShop<>(10);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        //CarController cc = new CarController();

        carC.cars.add(new Volvo240());
        carC.cars.add(new Saab95());
        carC.cars.add(new Scania());

        carC.cars.get(0).x = 0; carC.cars.get(0).y = 0;
        carC.cars.get(1).x = 0; carC.cars.get(1).y = 100;
        carC.cars.get(2).x = 0; carC.cars.get(2).y = 200;

        for(Vehicle car : carC.cars) {
            car.turnRight();
        }

        // Start a new view and send a reference of self


        // Start the timer
        carC.timer.start();

        // Define what the buttons should do if they are pressed
        frame.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                frame.gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.gas(frame.gasAmount);
            }
        });
        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.brake(frame.gasAmount);
            }
        });
        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.turboOn();

            }
        });
        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.turboOff();

            }
        });
        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.liftBed();
            }
        });
        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.lowerBed();
            }
        });
        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.startEngine();
            }
        });
        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.stopEngine();
            }
        });


    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
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

            for (int i =0; i < cars.size(); i++) {
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

                frame.drawPanel.moveit(i,x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }
    void turboOn(){
        for(Vehicle car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void turboOff(){
        for(Vehicle car: cars){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void liftBed(){
        for(Vehicle car : cars) {
            if (car instanceof Scania){
                ((Scania) car).raiseBed();
            }
        }
    }
    void lowerBed(){
        for(Vehicle car : cars){
            if (car instanceof Scania) {
                while(((Scania) car).getAngle() > 0) {
                ((Scania) car).lowerBed();
                }
            }
        }
    }
    void startEngine() {
        for(Vehicle car : cars) {
            car.startEngine();
        }
    }
    void stopEngine() {
        for(Vehicle car : cars) {
            car.stopEngine();
        }
    }
}
