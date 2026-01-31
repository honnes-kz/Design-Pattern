abstract class CelestialBody {

    private String name;
    private double x, y;

    protected CelestialBody(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public double distanceTo(CelestialBody other) {
        if (other == null) throw new IllegalArgumentException();
        double dx = other.x - x;
        double dy = other.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

class Planet extends CelestialBody {

    private String atmosphereType;

    public Planet(String name, double x, double y, String atmosphereType) {
        super(name, x, y);
        this.atmosphereType = atmosphereType;
    }

    public String getAtmosphereType() {
        return atmosphereType;
    }
}

class SpaceStation extends CelestialBody {
    private int level;

    public SpaceStation(String name, double x, double y, int level) {
        super(name, x, y);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

class Cargo {
    private double weightKg;
    private String description;

    public Cargo(double weightKg, String description) {
        if (weightKg <= 0) throw new IllegalArgumentException();
        this.weightKg = weightKg;
        this.description = description;
    }

    public double getWeightKg() {
        return weightKg;
    }
    public String getDescription() {
        return description;
    }
}

enum DroneStatus {
    IDLE, IN_FLIGHT
}

abstract class Drone {
    private String id;
    private DroneStatus status;
    private double maxPayloadKg;

    protected Drone(String id, double maxPayloadKg) {
        if (maxPayloadKg <= 0) throw new IllegalArgumentException();
        this.id = id;
        this.maxPayloadKg = maxPayloadKg;
        this.status = DroneStatus.IDLE;
    }

    public String getId() {
        return id;
    }
    public DroneStatus getStatus() {
        return status;
    }
    public double getMaxPayloadKg() {
        return maxPayloadKg;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }
    public abstract double speedKmPerMin();
}

class HeavyDrone extends Drone {
    public HeavyDrone(String id, double maxPayloadKg) {
        super(id, maxPayloadKg);
    }
    public double speedKmPerMin() { return 5.0; }
}

class LightDrone extends Drone {
    public LightDrone(String id, double maxPayloadKg) {
        super(id, maxPayloadKg);
    }
    public double speedKmPerMin() {
        return 10.0;
    }
}

public class Main {
    public static void main(String[] args) {

        Planet earth = new Planet("Earth", 0, 0, "Oxygen-Nitrogen");
        SpaceStation alpha = new SpaceStation("Alpha", 100, 100, 5);

        System.out.println("Distance from Earth to Alpha: " + earth.distanceTo(alpha));

        Cargo cargo1 = new Cargo(50, "Food supplies");

        HeavyDrone hd = new HeavyDrone("hd-01", 100);
        LightDrone ld = new LightDrone("ld-01", 20);

        System.out.println(hd.getId() + " speed: " + hd.speedKmPerMin());
        System.out.println(ld.getId() + " speed: " + ld.speedKmPerMin());

        hd.setStatus(DroneStatus.IN_FLIGHT);
        ld.setStatus(DroneStatus.IN_FLIGHT);

        System.out.println(hd.getId() + " status: " + hd.getStatus());
        System.out.println(ld.getId() + " status: " + ld.getStatus());

        System.out.println("Cargo: " + cargo1.getDescription() + ", weight: " + cargo1.getWeightKg() + " kg");
    }
}

