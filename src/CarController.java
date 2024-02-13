import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

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
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();
    AutoRepairShop<Volvo240> volvoWorkshop = new AutoRepairShop<>(10);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        cc.cars.get(0).x = 0; cc.cars.get(0).y = 0;
        cc.cars.get(1).x = 0; cc.cars.get(1).y = 100;
        cc.cars.get(2).x = 0; cc.cars.get(2).y = 200;

        for(Vehicle car : cc.cars) {
            car.direction = 1;
        }

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
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
            final int RADIUS = 100;
            int workshopX = DrawPanel.getImageCoordinates(3)[0];
            int workshopY = DrawPanel.getImageCoordinates(3)[1];

            // Make sure the cars cannot go outside the frame
            for (int i =0; i < cars.size(); i++) {
                Vehicle car = cars.get(i);

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
                ((Scania) car).lowerBed();
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
