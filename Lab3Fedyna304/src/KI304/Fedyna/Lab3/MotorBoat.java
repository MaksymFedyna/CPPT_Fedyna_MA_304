package KI304.Fedyna.Lab3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

// Інтерфейс для руху
interface Movable {
    void move(double distance);
}

// Інтерфейс для потужності та пального
interface Powered extends Movable {
    double fuelStatus(); // метод для перевірки залишку пального
    void refuel(double liters); // метод для заправки човна
}

// Абстрактний клас для транспортного засобу
abstract class Vehicle {
    protected String name;
    protected double distanceTravelled;
    protected BufferedWriter logWriter;

    public Vehicle(String name) throws IOException {
        this.name = name;
        this.distanceTravelled = 0;
        logWriter = new BufferedWriter(new FileWriter("MotorBoatLog.txt", true));
        log("KI304.Fedyna.Lab3.Vehicle created with name: " + name);
    }

    // Метод для запису в лог
    protected void log(String message) {
        try {
            logWriter.write(LocalDateTime.now() + ": " + message + "\n");
            logWriter.flush();
        } catch (IOException e) {
            System.err.println("Error writing to log: " + e.getMessage());
        }
    }

    // Абстрактний метод для отримання статусу пального (повинен бути реалізований в дочірніх класах)
    public abstract double fuelStatus();

    // Метод для закриття логу
    public void closeLog() {
        try {
            logWriter.close();
        } catch (IOException e) {
            System.err.println("Error closing log: " + e.getMessage());
        }
    }
}

// Клас для двигуна
class Engine {
    private double fuelCapacity;
    private double fuelLevel;

    public Engine(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
        this.fuelLevel = fuelCapacity;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void consumeFuel(double amount) {
        if (fuelLevel >= amount) {
            fuelLevel -= amount;
        } else {
            fuelLevel = 0;
        }
    }

    public void refuel(double liters) {
        fuelLevel = Math.min(fuelCapacity, fuelLevel + liters);
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }
}

// Клас для керма човна
class Steering {
    public void turnLeft() {
        System.out.println("Turning left.");
    }

    public void turnRight() {
        System.out.println("Turning right.");
    }
}

// Основний клас для Моторного човна
public class MotorBoat extends Vehicle implements Powered {
    private Engine engine;
    private Steering steering;

    // Конструктор із параметрами
    public MotorBoat(String name, double fuelCapacity) throws IOException {
        super(name);
        this.engine = new Engine(fuelCapacity);
        this.steering = new Steering();
        log("KI304.Fedyna.Lab3.MotorBoat created with name: " + name);
    }

    // Дефолтний конструктор
    public MotorBoat() throws IOException {
        this("Unnamed Boat", 50);
    }

    // Метод для руху
    @Override
    public void move(double distance) {
        double fuelNeeded = distance / 10; // Припустимо, 10 км на літр
        if (engine.getFuelLevel() >= fuelNeeded) {
            distanceTravelled += distance;
            engine.consumeFuel(fuelNeeded);
            log("Moved " + distance + " km. Remaining fuel: " + engine.getFuelLevel());
        } else {
            log("Not enough fuel to move " + distance + " km.");
        }
    }

    // Метод для перевірки пального
    @Override
    public double fuelStatus() {
        return engine.getFuelLevel();
    }

    // Метод для заправки човна
    @Override
    public void refuel(double liters) {
        engine.refuel(liters);
        log("Refueled " + liters + " liters. Current fuel level: " + engine.getFuelLevel());
    }

    public void turnLeft() {
        steering.turnLeft();
        log("Turned left.");
    }

    public void turnRight() {
        steering.turnRight();
        log("Turned right.");
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }
}
