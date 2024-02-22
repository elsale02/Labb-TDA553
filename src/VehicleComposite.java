import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleComposite implements Movable {
    private final Random random = new Random();
    private final Vehicle[] CAR_TYPES = new Vehicle[] {CarFactory.createVolvo(0,0,0),
                                                        CarFactory.createSaab(0,0,0),
                                                        CarFactory.createScania(0,0,0)};
    private List<Vehicle> carList = new ArrayList<>();
    public void addCar(Vehicle vehicle){
        if(carList.size() < 10) {
            carList.add(vehicle);
        }
    }

    public void addCar(){
        int index = random.nextInt(CAR_TYPES.length);
        addCar(CAR_TYPES[index]);
    }

    public void removeCar(Vehicle vehicle){
        if(!carList.isEmpty()) {
            carList.remove(vehicle);
        }
    }

    public void setColor(Vehicle vehicle) {

    }

    @Override
    public void move() {
        for(Vehicle car : carList) {
            car.move();
        }
    }
    @Override
    public void turnLeft() {
        for(Vehicle car : carList) {
            car.turnLeft();
        }
    }
    @Override
    public void turnRight( ) {
        for(Vehicle car : carList){
            car.turnRight();
        }
    }


    @Override
    public void startEngine(){
        for(Vehicle car : carList){
            car.startEngine();
        }
    }
    @Override
    public void stopEngine(){
      for(Vehicle car : carList) {
          car.stopEngine();
      }
    }
    @Override
    public void gas(double amount) {
        for(Vehicle car : carList) {
            car.gas(amount);
        }
    }
    @Override
    public void brake(double amount) {
        for(Vehicle car : carList) {
            car.brake(amount);
        }
    }
}
