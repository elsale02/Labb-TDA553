public abstract class BuildingFactory {
    public static AutoRepairShop<Volvo240> createVolvoWorkshop(int x, int y, int maxBays) {
        AutoRepairShop<Volvo240> workshop = new AutoRepairShop<>(maxBays);
        workshop.setX(x);
        workshop.setY(y);
        return workshop;
    }
}
