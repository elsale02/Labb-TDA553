public class CarFactory {
    private final Vehicle vehicle;
    private int x;
    private int y;
    private int direction;
    public CarFactory(Vehicle vehicle, int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.vehicle = vehicle;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getDirection() {
        return this.direction;
    }
}
