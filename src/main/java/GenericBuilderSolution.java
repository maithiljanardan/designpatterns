
/**
 * This will let you create complex objects with  different types and representations using the same construction code
 * Builder design pattern suggests that you extract the object construction code out of its class and move it to a separate objects called builders
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 * <p>
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 */

/**
 * Different Types(house, house with four walls, house with four walls and two windows) and Representations(house made of stones, house with four walls
 * and two windows made of wood)
 *
 */

/**
 * Implementation Steps
 *
 * 1. Make sure that you can clearly define the common construction steps for building all available product representations.
 *  Otherwise, you won’t be able to proceed with implementing the pattern.
 *
 * 2.Declare these steps in the base builder interface.
 *
 * 3.Create a concrete builder class for each of the product representations and implement their construction steps.
 *
 * 4. Don’t forget about implementing a method for fetching the result of the construction.
 * The reason why this method can’t be declared inside the builder interface is that various builders may construct products that don’t have a common interface.
 * Therefore, you don’t know what would be the return type for such a method. However, if you’re dealing with products from a single hierarchy,
 * the fetching method can be safely added to the base interface.
 *
 * 5.Think about creating a director class. It may encapsulate various ways to construct a product using the same builder object.
 *
 * 6.The client code creates both the builder and the director objects. Before construction starts, the client must pass a builder object to the director.
 * Usually, the client does this only once, via parameters of the director’s constructor. The director uses the builder object in all further construction.
 * There’s an alternative approach, where the builder is passed directly to the construction method of the director.
 *
 * The construction result can be obtained directly from the director only if all products follow the same interface. Otherwise, the client should fetch the result from the builder.
 */
public class GenericBuilderSolution {

  public static void main(String[] args) {

    CarBuilder builder = new CarBuilder();
    Director.constructSportsCar(builder);
    Car car = builder.getResult();
    System.out.println(car);

    CarManualBuilder manualBuilder = new CarManualBuilder();
    Director.constructSportsCar(manualBuilder);
    Manual manual = manualBuilder.getResult();
    System.out.println(manual);
  }

  private static class Director {
    public static void constructSportsCar(Builder builder) {
      builder.setCarType(CarType.SPORTS_CAR);
      builder.setSeats(2);
      builder.setEngine(new Engine(3.0, 0));
      builder.setTransmission(Transmission.SEMI_AUTOMATIC);
      builder.setTripComputer(new TripComputer());
      builder.setGPSNavigator(new GPSNavigator());
    }

    public static void constructCityCar(Builder builder) {
      builder.setCarType(CarType.CITY_CAR);
      builder.setSeats(2);
      builder.setEngine(new Engine(1.2, 0));
      builder.setTransmission(Transmission.AUTOMATIC);
      builder.setTripComputer(new TripComputer());
      builder.setGPSNavigator(new GPSNavigator());
    }

    public static void constructSUVCar(Builder builder) {
      builder.setCarType(CarType.SUV);
      builder.setSeats(4);
      builder.setEngine(new Engine(2.5, 0));
      builder.setTransmission(Transmission.MANUAL);
      builder.setGPSNavigator(new GPSNavigator());
    }
  }

  private interface Builder {
    void setCarType(CarType carType);

    void setSeats(int seats);

    void setEngine(Engine engine);

    void setTransmission(Transmission transmission);

    void setTripComputer(TripComputer tripComputer);

    void setGPSNavigator(GPSNavigator gpsNavigator);
  }

  private static class CarBuilder implements Builder {

    private CarType carType;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType carType) {
      this.carType = carType;
    }

    @Override
    public void setSeats(int seats) {
      this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
      this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
      this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
      this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
      this.gpsNavigator = gpsNavigator;
    }

    public Car getResult() {
      return new Car(carType, seats, engine, transmission, tripComputer, gpsNavigator);
    }
  }

  private static class CarManualBuilder implements Builder {

    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType type) {
      this.type = type;
    }

    @Override
    public void setSeats(int seats) {
      this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
      this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
      this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
      this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
      this.gpsNavigator = gpsNavigator;
    }

    public Manual getResult() {
      return new Manual(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
  }

  private enum CarType {
    CITY_CAR, SPORTS_CAR, SUV
  }

  public enum Transmission {
    SINGLE_SPEED, MANUAL, AUTOMATIC, SEMI_AUTOMATIC
  }

  private static class Engine {

    private final double volume;
    private double mileage;
    private boolean started;

    public Engine(double volume, double mileage) {
      this.volume = volume;
      this.mileage = mileage;
    }

    public void on() {
      started = true;
    }

    public void off() {
      started = false;
    }

    public boolean isStarted() {
      return started;
    }

    public void go(double mileage) {
      if (started) {
        this.mileage += mileage;
      } else
        System.out.println("Cannot go(), you must start engine first");
    }

    public double getVolume() {
      return volume;
    }

    public double getMileage() {
      return mileage;
    }
  }

  private static class TripComputer {

    private Car car;

    public void setCar(Car car) {
      this.car = car;
    }

    public void showFuelLevel() {
      System.out.println("Fuel Level: " + car.getFuel());
    }

    public void showStatus() {
      if (this.car.getEngine().isStarted()) {
        System.out.println("Car is started");
      } else {
        System.out.println("Car isn't started");
      }
    }
  }

  private static class GPSNavigator {
    private final String route;

    public GPSNavigator() {
      this.route = "221b, Baker Street, London to Scotland Yard, 8-10 Broadway, London";
    }

    public GPSNavigator(String manualRoute) {
      this.route = manualRoute;
    }

    public String getRoute() {
      return route;
    }
  }

  private static class Car {

    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;
    private double fuel = 0;

    public Car(CarType carType, int seats, Engine engine, Transmission transmission,
               TripComputer tripComputer, GPSNavigator gpsNavigator) {
      this.carType = carType;
      this.seats = seats;
      this.engine = engine;
      this.transmission = transmission;
      this.tripComputer = tripComputer;
      if (this.tripComputer != null) {
        this.tripComputer.setCar(this);
      }
      this.gpsNavigator = gpsNavigator;
    }

    public CarType getCarType() {
      return carType;
    }

    public double getFuel() {
      return fuel;
    }

    public void setFuel(double fuel) {
      this.fuel = fuel;
    }

    public int getSeats() {
      return seats;
    }

    public Engine getEngine() {
      return engine;
    }

    public Transmission getTransmission() {
      return transmission;
    }

    public TripComputer getTripComputer() {
      return tripComputer;
    }

    public GPSNavigator getGpsNavigator() {
      return gpsNavigator;
    }

    @Override
    public String toString() {
      return "Car{" +
          "carType=" + carType +
          ", seats=" + seats +
          ", engine=" + engine +
          ", transmission=" + transmission +
          ", tripComputer=" + tripComputer +
          ", gpsNavigator=" + gpsNavigator +
          ", fuel=" + fuel +
          '}';
    }
  }

  private static class Manual {
    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;

    public Manual(CarType carType, int seats, Engine engine, Transmission transmission,
                  TripComputer tripComputer, GPSNavigator gpsNavigator) {
      this.carType = carType;
      this.seats = seats;
      this.engine = engine;
      this.transmission = transmission;
      this.tripComputer = tripComputer;
      this.gpsNavigator = gpsNavigator;
    }

    public String print() {
      String info = "";
      info += "Type of car: " + carType + "\n";
      info += "Count of seats: " + seats + "\n";
      info += "Engine: volume - " + engine.getVolume() + "; mileage - " + engine.getMileage() + "\n";
      info += "Transmission: " + transmission + "\n";
      if (this.tripComputer != null) {
        info += "Trip Computer: Functional" + "\n";
      } else {
        info += "Trip Computer: N/A" + "\n";
      }
      if (this.gpsNavigator != null) {
        info += "GPS Navigator: Functional" + "\n";
      } else {
        info += "GPS Navigator: N/A" + "\n";
      }
      return info;
    }

    @Override
    public String toString() {
      return "Manual{" +
          "carType=" + carType +
          ", seats=" + seats +
          ", engine=" + engine +
          ", transmission=" + transmission +
          ", tripComputer=" + tripComputer +
          ", gpsNavigator=" + gpsNavigator +
          '}';
    }
  }

}
