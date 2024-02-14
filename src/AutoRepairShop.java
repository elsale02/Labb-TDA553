import java.util.ArrayList;
import java.util.List;

public class AutoRepairShop <T extends Car>  {
    private final ArrayList<T> carBays;
    private int maxBays;
    public AutoRepairShop(int maxBays){
        this.maxBays = maxBays;
        carBays = new ArrayList<>();
    }
    public AutoRepairShop(T[] cars){
        this.maxBays = cars.length;
        carBays = new ArrayList<>(List.of(cars));
    }
    public int getCarCount(){
        return carBays.size();
    }
    public void storeCar(T car){
        if(carBays.size() < maxBays) {
            carBays.add(car);
        }
    }
    public void removeCar(T car){
        carBays.remove(car);
    }
}
