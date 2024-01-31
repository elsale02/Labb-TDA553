import java.awt.*;
import java.util.ArrayList;
public class CarTransport extends Car implements HasBed{
    private final ArrayList<Car> carLoad;
    private final Bed bed;
    private final int capacity;

    public CarTransport() {
        super(2, Color.yellow,250,"Långtradare modell 1");
        this.capacity = 10;
        bed = new Bed();
        carLoad = new ArrayList<>();
    }

    @Override
    public void move() {
        super.move();
        for(Car car : carLoad) {
            car.currentSpeed = this.currentSpeed;
            car.direction = this.direction;
            car.move();
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
            bed.setAngle(0);
        }
    }

    @Override
    public void raiseBed() {
        if(currentSpeed == 0) {
            bed.setAngle(70);
        }
    }

    public void releaseCar() {
        if(bed.getAngle() == 0) {
            if (!carLoad.isEmpty()) {
                Car releasedCar = carLoad.removeFirst();
                releasedCar.y--;
            }
        }
    }

    public void loadCar(Car car) {
        if(carLoad.size() < capacity) {
            double radius = 1;
            // Only allow if car object is not CarTransport ad if bed is lowered
            if (car.getClass() != CarTransport.class && bed.getAngle() == 0) {
                if (Math.abs(car.x) <= radius && Math.abs(car.y) <= radius) {
                    carLoad.addFirst(car);
                }
            }
        }
    }
}
