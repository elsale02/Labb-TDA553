import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
public class testCar {
    private Saab95 saab;
    private Volvo240 volvo;
    private Scania scania;
    private AutoRepairShop<Volvo240> volvoRepair;

    private AutoRepairShop<Car> carRepair;
    private CarTransport carTransport;

    @Before
    public void setup(){
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
        volvoRepair = new AutoRepairShop<>(10);
        carRepair = new AutoRepairShop<>(10);
        carTransport = new CarTransport();
    }

    @Test
    public void testRaiseBedScania(){
        Scania scania = new Scania();
        scania.raiseBed();
        assertTrue(scania.getAngle() >= 10 && scania.getAngle() < 70);
    }

    @Test
    public void testLowerBedScania(){
        Scania scania = new Scania();
        for(int i = 0; i < 5; i++){
            scania.raiseBed();
        }
        scania.lowerBed();
        assertTrue(scania.getAngle() == 40);
    }
    

    @Test
    public void testVolvoChangeColor(){
        volvo.setColor(Color.red);
        assertEquals(volvo.getColor(), Color.red);
    }
    @Test
    public void testSaabChangeColor(){
        saab.setColor(Color.black);
        assertEquals(saab.getColor(), Color.black);
    }
    @Test
    public void testTurnLeftThenMove(){
        saab.startEngine();
        saab.turnLeft();
        saab.move();
        assertTrue(saab.x < 0);
    }
    @Test
    public void testTurnRightThenMove(){
        saab.startEngine();
        saab.turnRight();
        saab.move();
        assertTrue(saab.x > 0);
    }
    @Test
    public void testTurnRightThenLeft(){
        saab.turnRight();
        saab.turnLeft();
        assertEquals(0, saab.direction);
    }
    @Test
    public void testDriveInCircle(){
        double oldX = saab.x;
        double oldY = saab.y;
        for(int i = 0; i < 4; i++){
            saab.move();
            saab.turnRight();
        }
        assertTrue(saab.direction == 0 && Math.abs(saab.x - oldX) < 0.0001 &&
                                                  Math.abs(saab.y - oldY) < 0.0001);
    }
    @Test
    public void testRotateCircle(){
        for(int i = 0; i < 4; i++) {
            saab.turnRight();
        }
        assertEquals(0, saab.direction);
    }
    @Test
    public void testTooLowGasAmount() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    saab.gas(-1);

                });
        String expectedMessage = "Wrong gas amount. Insert amount between 0 and 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testTooHighGasAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    saab.gas(1.5);

                });
        String expectedMessage = "Wrong gas amount. Insert amount between 0 and 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
    @Test
    public void testTooLowBreakAmount() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> {
                        saab.brake(-1);

                    });
            String expectedMessage = "Wrong break amount. Insert amount between 0 and 1";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testTooHighBreakAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    saab.brake(1.5);

                });
        String expectedMessage = "Wrong break amount. Insert amount between 0 and 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testIncreaseSpeed(){
        saab.stopEngine(); // speed == 0
        saab.incrementSpeed(0.5); // min(0 + speedFactor*0.5, enginePower)
        // Turbo on = false; EnginePower = 125 --> speedFactor == 125 * 0.01 * 1 == 1.25
        // new speed = min(1.25 * 0.5 = 0.625, 125) --> 0.625
        double newSpeed = saab.getCurrentSpeed();
        assertEquals(newSpeed,0, 0.0001);
    }
    @Test
    public void testDecreaseSpeed(){
        saab.stopEngine(); // speed == 0
        saab.decrementSpeed(0.5); //Turbo on = false; EnginePower = 125
        // speedFactor == 125 * 0.01 * 1 == 1.25
        // new speed = -1.25 * 0.5 = -0.625 --> 0
        double newSpeed = saab.getCurrentSpeed();
        assertEquals(newSpeed,0, 0.0001);
    }
    @Test
    public void testSpeedTooHigh(){
        saab.currentSpeed = 200;
        saab.incrementSpeed(1);
        assertEquals(saab.currentSpeed,saab.enginePower, 0.0001);
    }
    @Test
    public void testSpeedTooLow(){
        saab.currentSpeed = -10;
        saab.decrementSpeed(1);
        assertEquals(saab.currentSpeed,0, 0.0001);
    }
    @Test
    public void testSpeedLowerLimitLock(){
        saab.startEngine();
        saab.currentSpeed = -10;
        saab.incrementSpeed(1); // New speed = 1 * 125*0.01*1 = 1.25
        assertEquals(saab.getCurrentSpeed(),0,0.0001);
    }
    @Test
    public void testSpeedUpperLimitLock(){
        saab.currentSpeed = saab.enginePower + 1000;
        saab.decrementSpeed(1); // New speed = 125
        assertEquals(saab.getCurrentSpeed(),125,0.0001);
    }
    @Test
    public void testCheckDoors(){
        assertEquals(2,saab.getNrDoors());
    }
    @Test
    public void testCheckEnginePower(){
        assertTrue(saab.getEnginePower() - 125 < 0.0001);
    }
    @Test
    public void testMoveInvalidDirection(){
        saab.direction = 5;
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    saab.move();

                });
        String expectedMessage = "Direction out of range [0,3]";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
    @Test
    public void testTurboTurnOn(){
        double oldSpeedFactor = saab.speedFactor();
        saab.setTurboOn();
        assertTrue(saab.speedFactor() > oldSpeedFactor);
    }
    @Test
    public void testTurboTurnOff(){
        double oldSpeedFactor = saab.speedFactor();
        saab.setTurboOn();
        saab.setTurboOff();
        assertTrue(saab.speedFactor() - oldSpeedFactor < 0.0001);
    }
    @Test
    public void testCheckSpeedFactor(){
        assertTrue(volvo.speedFactor() - 1.25 < 0.0001);

    }

    @Test
    public void testGasIncrementSpeed(){
        saab.startEngine();
        double gasCurrentSpeed = saab.currentSpeed;
        saab.gas(0.5);
        assertTrue(saab.getCurrentSpeed() > gasCurrentSpeed);
    }

    @Test
    public void testBrakeDecrementSpeed(){
        saab.startEngine();
        double oldSpeed = saab.currentSpeed;
        saab.brake(0.5);
        assertTrue(saab.getCurrentSpeed() < oldSpeed);
    }

    @Test
    public void testStoreMoreThanMaxCarsToAutoRepairShop(){
        AutoRepairShop<Car> shop = new AutoRepairShop<>(1);
        shop.storeCar(volvo);
        shop.storeCar(saab);
        assertEquals(shop.getCarCount(), 1);
    }
    @Test
    public void testRemoveFromEmptyAutoRepairShop(){
        carRepair = new AutoRepairShop<>(new Car[] {volvo});
        carRepair.removeCar(volvo);
        carRepair.removeCar(saab);
        assertEquals(carRepair.getCarCount(), 0);
    }

    @Test
    public void autoRepairShopSpecificModel(){
        volvoRepair.storeCar(volvo);
        assertTrue(volvoRepair.getCarCount() > 0);
    }
    @Test
    public void TestScaniaSpeedFactor(){
        double factor = scania.speedFactor();
        assertEquals(factor, 1.61, 0.0001);
    }
    @Test
    public void TestScaniaStartEngineWithLoweredBed(){
        for(int i = 0; i < 9; i++) {
            scania.lowerBed();
        }
        scania.startEngine();
        assertEquals(scania.getCurrentSpeed(), 0.1, 0.0001);
    }
    @Test
    public void TestScaniaStartEngineWithRaisedBed(){
        for(int i = 0; i < 9; i++) {
            scania.raiseBed();
        }
        scania.startEngine();
        assertEquals(scania.getCurrentSpeed(), 0, 0.0001);
    }

    @Test
    public void TestCarTransportMoveWithLoadedCar(){
        carTransport.startEngine();
        carTransport.loadCar(volvo);
        carTransport.move();
        assertTrue(volvo.y > 0);
    }

    @Test
    public void TestCarTransportReleaseCar(){
        carTransport.loadCar(volvo);
        carTransport.releaseCar();
        assertTrue(volvo.y < 0);
    }

    @Test
    public void TestCarTransportLoadOutOfRange(){
        volvo.startEngine();
        for(int i = 0; i < 1000; i++) {
            volvo.move();
        }
        carTransport.loadCar(volvo);
        assertTrue(carTransport.getCarLoad().isEmpty());
    }

}
