public class Bed implements HasBed {
    private int angle;

    public Bed() {
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
    public int getAngle() {
        return this.angle;
    }
    @Override
    public void raiseBed() {
        angle = 70;
    }
    @Override
    public void lowerBed() {
        angle = 0;
    }
}
