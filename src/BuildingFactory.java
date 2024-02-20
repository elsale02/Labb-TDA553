public abstract class BuildingFactory {
    public static <T extends Car> AutoRepairShop<T> createWorkshop(int x, int y, int maxBays) {
        AutoRepairShop<T> workshop = new AutoRepairShop<>(maxBays);
        workshop.setX(x);
        workshop.setY(y);
        return workshop;
    }
}
