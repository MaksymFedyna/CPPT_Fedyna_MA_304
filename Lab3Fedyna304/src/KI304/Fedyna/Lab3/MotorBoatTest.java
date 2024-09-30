package KI304.Fedyna.Lab3;

import java.io.IOException;

public class MotorBoatTest {
    public static void main(String[] args) {
        try {
            MotorBoat boat = new MotorBoat("Speedy", 100);
            boat.move(50);
            boat.turnLeft();
            boat.refuel(20);
            boat.move(150);
            boat.turnRight();
            boat.closeLog();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
