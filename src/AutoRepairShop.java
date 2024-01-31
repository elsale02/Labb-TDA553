import java.util.ArrayList;

public class AutoRepairShop {
    private final ArrayList<Car> carBays;
    private final int maxBays;
    private final ArrayList<String> allowedModels;
    public AutoRepairShop(int maxBays, ArrayList<String> allowedModels) {
        this.maxBays = maxBays;
        carBays = new ArrayList<>();
        this.allowedModels = allowedModels;
    }

    public void storeCar(Car car){
        if(allowedModels.contains(car.modelName) && carBays.size() < maxBays) {
            carBays.add(car);
        }
    }
    public void removeCar(){
        System.out.println(carBays.getClass());
        carBays.removeLast();

    }
}
