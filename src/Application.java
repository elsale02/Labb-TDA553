import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        List<Building> buildings = new ArrayList<>();
        vehicles.add(CarFactory.createVolvo(0,0,1));
        vehicles.add(CarFactory.createSaab(0,100,1));
        vehicles.add(CarFactory.createScania(0,200,1));

        buildings.add(BuildingFactory.createWorkshop(300,300,10));

        // // // // // // // // // //

        CarController.carC.cars.add(new Volvo240());
        CarController.carC.cars.add(new Saab95());
        CarController.carC.cars.add(new Scania());

        CarController.carC.cars.get(0).x = 0; CarController.carC.cars.get(0).y = 0;
        CarController.carC.cars.get(1).x = 0; CarController.carC.cars.get(1).y = 100;
        CarController.carC.cars.get(2).x = 0; CarController.carC.cars.get(2).y = 200;

        for(Vehicle car : CarController.carC.cars) {
            car.turnRight();
        }
        CarController.inputListeners();
    }
}
