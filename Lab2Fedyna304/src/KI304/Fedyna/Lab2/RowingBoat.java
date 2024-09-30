package KI304.Fedyna.Lab2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RowingBoat {
    private Oars oars;
    private Seats seats;
    private Hull hull;
    private PrintWriter logWriter;
    private String direction = "Stationary";
    private int speed = 0;
    private double distanceTraveled = 0.0;
    private int fatigue = 0; // Fatigue level, max 100
    private double rowingEfficiency = 1.0; // Efficiency level, range from 0.0 to 1.0

    // Constructor
    public RowingBoat() throws FileNotFoundException {
        this.oars = new Oars();
        this.seats = new Seats(4);
        this.hull = new Hull("Wood");
        logWriter = new PrintWriter(new File("boatLog.txt"));
        logAction("Rowing boat created with wooden hull and 4 seats.");
    }

    // Rowing function
    public void row() {
        if (fatigue >= 100) {
            logAction("Oarsmen are too tired to row.");
            stopRowing();
            return;
        }

        if (speed == 0) {
            speed = calculateSpeed();
        }
        logAction("Rowing started at speed: " + speed + " km/h in direction: " + direction);
        fatigue += 10; // Increase fatigue with each rowing session
        rowingEfficiency = calculateEfficiency();
    }

    // Calculate rowing speed based on oar count, efficiency, and fatigue
    private int calculateSpeed() {
        int baseSpeed = 5; // Default base speed
        return (int) (baseSpeed * oars.getCount() * rowingEfficiency * (1 - fatigue / 100.0));
    }

    // Stop rowing
    public void stopRowing() {
        logAction("Rowing stopped.");
        speed = 0;
    }

    // Set new direction
    public void setDirection(String newDirection) {
        this.direction = newDirection;
        logAction("Direction changed to: " + direction);
    }

    // Travel distance (mocked value for distance calculation)
    public void travel(double distance) {
        distanceTraveled += distance;
        logAction("Traveled distance: " + distance + " km. Total distance: " + distanceTraveled + " km.");
    }

    // Change material of the boat
    public void changeMaterial(String newMaterial) {
        hull.setMaterial(newMaterial);
        logAction("Hull material changed to: " + newMaterial);
    }

    // Rearrange or change the number of seats
    public void rearrangeSeats(int newSeatCount) {
        seats.setCount(newSeatCount);
        logAction("Seats rearranged. New seat count: " + newSeatCount);
    }

    // Method to check oar synchronization
    public void syncOars(int syncLevel) {
        if (syncLevel > 100) syncLevel = 100;
        if (syncLevel < 0) syncLevel = 0;
        rowingEfficiency = syncLevel / 100.0;
        logAction("Oar synchronization adjusted to: " + syncLevel + "%. Efficiency: " + rowingEfficiency);
    }

    // Fatigue recovery
    public void rest(int time) {
        fatigue -= time;
        if (fatigue < 0) fatigue = 0;
        logAction("Resting for " + time + " minutes. Fatigue level: " + fatigue);
    }

    // Additional getter for distance traveled
    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public String getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFatigue() {
        return fatigue;
    }

    // Method to log actions with a timestamp
    private void logAction(String action) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        logWriter.println(timestamp + " - " + action);
        logWriter.flush();
    }

    public void dispose() {
        logWriter.close();
    }

    // Helper to calculate efficiency based on fatigue and synchronization
    private double calculateEfficiency() {
        return Math.max(0.1, rowingEfficiency * (1 - fatigue / 100.0));
    }
}
