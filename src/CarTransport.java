import java.util.ArrayList;
public class CarTransport extends Car{
    private final ArrayList<Car> carLoad;
    private boolean rampLowered;

    @Override
    public double speedFactor(){
        double loadWeight = 0.8;
        return enginePower * 0.01 * Math.pow(loadWeight,carLoad.size());
    }
    public void raiseRamp() {
        rampLowered = false;
    }
    public void lowerRamp() {
        if (currentSpeed == 0) {
            rampLowered = true;
        }
    }
    public void loadCar(Car car){
        double radius = 1;
        if(Math.abs(car.x) <= radius && Math.abs(car.y) <= radius) {
            carLoad.add(car);
        }
    }

    public void unloadCar(Car car) {
        if(carLoad.remove(car)) {
            car.y--;
        }
    }
}
