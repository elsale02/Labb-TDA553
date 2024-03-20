import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Vehicle implements Movable, VehicleObservable {

    private final int nrDoors; // Number of doors on the car
    protected final double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    protected final String modelName; // The car model name
    protected int x = 0;
    protected int y = 0;
    protected int direction = 0; // 0 = Upp, 1 == Höger, 2 = Ner, 3 = Vänster
    public Vehicle(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
    }
    @Override
    public void move(){
        double speed = getCurrentSpeed();
        switch(direction){
            case 0:
                //Go up
                y += speed;
                break;
            case 1:
                // Go right
                x += speed;
                break;
            case 2:
                // Go down
                y -= speed;
                break;
            case 3:
                // Go left
                x -= speed;
                break;
            default:
                throw new IllegalArgumentException("Direction out of range [0,3]");
        }
        stateChanged();
    }
    @Override
    public void turnLeft() {
        direction = (direction + 3) % 4;
    }
    @Override
    public void turnRight() {
        direction = (direction + 1) % 4;
    }
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color clr){
        color = clr;
    }
    public void startEngine(){
        if(currentSpeed < 0.0001) {
        currentSpeed = 0.1;
        }
    }
    public void stopEngine(){
        currentSpeed = 0;
    }
    public abstract double speedFactor();
    private void incrementSpeed(double amount){
        if(getCurrentSpeed() <= 0){
            currentSpeed = 0;
        } else {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        }
    }
    private void decrementSpeed(double amount){
        if (getCurrentSpeed() >= enginePower){
            currentSpeed = enginePower;
        } else {
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        }
    }
    public void gas(double amount){
        if(amount < 0 || amount > 1){
            throw new IllegalArgumentException("Wrong gas amount. Insert amount between 0 and 1");
        }
        incrementSpeed(amount);
    }
    public void brake(double amount){
        if(amount < 0 || amount > 1){
            throw new IllegalArgumentException("Wrong break amount. Insert amount between 0 and 1");
        }
        decrementSpeed(amount);
    }
    @Override
    public void subscribe(VehicleObserver subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(VehicleObserver subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void stateChanged(){
        for(VehicleObserver subscriber : VehicleObservable.subscribers) {
            subscriber.update();
        }
    }
    public abstract BufferedImage getImage();
}
