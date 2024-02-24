import java.util.ArrayList;
import java.util.List;

public interface VehicleObservable {
    List<VehicleObserver> subscribers = new ArrayList<>();
    void subscribe(VehicleObserver subscriber);
    void unsubscribe(VehicleObserver subscriber);
    void stateChanged(int x, int y);
}
