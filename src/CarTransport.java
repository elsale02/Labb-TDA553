import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarTransport extends Truck  {
    private final List<Car> carLoad;
    private final int capacity;
    private boolean isRaised;
    public CarTransport() {
        super(2, Color.yellow,250,"Långtradare modell 1");
        this.capacity = 10;
        carLoad = new ArrayList<>();
    }
    public List<Car> getCarLoad(){
        return this.carLoad;
    }
    @Override
    public void move() {
        super.move();
        for(Car car : carLoad) {
            car.x = this.x;
            car.y = this.y;
        }
    }
    @Override
    public double speedFactor(){
        double loadWeight = 0.8;
        return enginePower * 0.01 * Math.pow(loadWeight,carLoad.size());
    }
    @Override
    public void lowerBed() {
        if(currentSpeed == 0) {
            isRaised = false;
        }
    }
    @Override
    public void raiseBed() {
        if(currentSpeed == 0) {
            isRaised = true;
        }
    }
    public void releaseCar() {
        if(!isRaised) {
            if (!carLoad.isEmpty()) {
                Car releasedCar = carLoad.removeFirst();
                releasedCar.y--;
            }
        }
    }
    public void loadCar(Car car) {
        if(carLoad.size() < capacity) {
            double radius = 1;
            if (!isRaised) {
                if (Math.abs(car.x) <= radius && Math.abs(car.y) <= radius) {
                    carLoad.addFirst(car);
                    car.x = this.x;
                    car.y = this.y;
                }
            }
        }
    }
}
