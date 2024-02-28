import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleComposite implements Movable {
    //private final Random random = new Random();
    /*private final Vehicle[] CAR_TYPES = new Vehicle[] {CarFactory.createVolvo(0,0,1),
                                                        CarFactory.createSaab(0,100,1),
                                                        CarFactory.createScania(0,200,1)};*/
    private List<Vehicle> carList = new ArrayList<>();

    public void addCar(Vehicle vehicle){
        if(carList.size() < 10) {
            carList.add(vehicle);
        }
    }
    public List<Vehicle> getCarList() {
        return carList;
    }
    public Vehicle getCar(int index) {
        return carList.get(index);
    }
    public int getCarCount() {
        return carList.size();
    }

    public void addCar(){
        addCar(CarFactory.createVolvo(100,100,1));
        //int index = random.nextInt(CAR_TYPES.length);
        //addCar(CAR_TYPES[index]);
    }

    public void removeCar(){
        if(carList.size() > 1) {
            carList.removeLast();
            DrawPanel.vehiclePoints.removeLast();
        }
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
