public abstract class CarFactory {
    public static Vehicle createVolvo(int x, int y, int direction) {
        Volvo240 volvo = new Volvo240();
        volvo.x = x;
        volvo.y = y;
        volvo.direction = direction;
        return volvo;
    }
    public static Vehicle createSaab(int x, int y, int direction) {
        Saab95 saab = new Saab95();
        saab.x = x;
        saab.y = y;
        saab.direction = direction;
        return saab;
    }
    public static Vehicle createScania(int x, int y, int direction) {
        Scania scania = new Scania();
        scania.x = x;
        scania.y = y;
        scania.direction = direction;
        return scania;
    }
    public static Vehicle createCarTransport(int x, int y, int direction) {
        CarTransport carTransport = new CarTransport();
        carTransport.x = x;
        carTransport.y = y;
        carTransport.direction = direction;
        return carTransport;
    }
}
