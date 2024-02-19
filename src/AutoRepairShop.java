import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class AutoRepairShop <T extends Car>  {
    private final Deque<T> carBays;
    private int maxBays;
    public AutoRepairShop(int maxBays){
        this.maxBays = maxBays;
        carBays = new ArrayDeque<>();
    }
    public AutoRepairShop(T[] cars){
        this.maxBays = cars.length;
        carBays = new ArrayDeque<>(List.of(cars));
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
