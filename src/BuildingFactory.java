import java.awt.*;

public abstract class BuildingFactory {
    public static AutoRepairShop<Volvo240> createVolvoWorkshop(int x, int y, int maxBays) {
        AutoRepairShop<Volvo240> workshop = new AutoRepairShop<>(maxBays);
        workshop.setX(x);
        workshop.setY(y);
        DrawPanel.buildingPoints.add(new Point(x,y));
        return workshop;
    }
    public static AutoRepairShop<Car> createCarWorkshop(int x, int y, int maxBays) {
        AutoRepairShop<Car> workshop = new AutoRepairShop<>(maxBays);
        workshop.setX(x);
        workshop.setY(y);
        DrawPanel.buildingPoints.add(new Point(x,y));
        return workshop;
    }
}
