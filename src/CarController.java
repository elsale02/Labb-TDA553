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
    Volvo240 volvo = new Volvo240();
    Saab95 saab = new Saab95();
    Scania scania = new Scania();
    ArrayList<Vehicle> cars = new ArrayList<>();

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

//        for(Vehicle car : cc.cars) {
//            car.direction = 1;
//        }

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
            for (Vehicle car : cars) {
                // When hit wall: stop, turn around, then start again
                boolean isOutOfFrame = car.x >= frame.getWidth() || car.x < 0 || car.y >= frame.getHeight() - 150 || car.y < 0;
                if(isOutOfFrame) {
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                    while(isOutOfFrame){
                        isOutOfFrame = car.x >= frame.getWidth() || car.x < 0 || car.y >= frame.getHeight() - 150 || car.y < 0;
                        car.move();
                    }
                }
                car.move();
                int x = (int) Math.round(car.x);
                int y = (int) Math.round(car.y);
                frame.drawPanel.moveit(x, y);
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
}
