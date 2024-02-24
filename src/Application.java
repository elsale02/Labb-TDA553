import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        DrawPanel view = new DrawPanel(800,600);

        VehicleComposite vehicles = new VehicleComposite();
        List<Building> buildings = new ArrayList<>();

        vehicles.addCar(CarFactory.createVolvo(0,0,1));
        vehicles.addCar(CarFactory.createSaab(0,100,1));
        vehicles.addCar(CarFactory.createScania(0,200,1));

        buildings.add(BuildingFactory.createVolvoWorkshop(300,300,10));

        VehicleObservable publisher = vehicles;
        publisher.subscribe(view);

        CarController cc = new CarController(vehicles, buildings);

        cc.inputListeners();
    }
}
