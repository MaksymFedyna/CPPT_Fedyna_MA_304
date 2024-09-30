package KI304.Fedyna.Lab2;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Клас-драйвер для демонстрації роботи класу RowingBoat з меню
 */
public class RowingBoatApp {
    public static void main(String[] args) {
        RowingBoat boat = null;
        try {
            boat = new RowingBoat();
        } catch (FileNotFoundException e) {
            System.out.println("Не вдалося створити файл логів: " + e.getMessage());
            return;
        }

        boolean continueApp = true;
        Scanner scanner = new Scanner(System.in);

        while (continueApp) {
            System.out.println("\n*** Rowing Boat Menu ***");
            System.out.println("1. Start Rowing");
            System.out.println("2. Stop Rowing");
            System.out.println("3. Change Direction");
            System.out.println("4. Travel Distance");
            System.out.println("5. Change Hull Material");
            System.out.println("6. Rearrange Seats");
            System.out.println("7. Sync Oars");
            System.out.println("8. Rest");
            System.out.println("9. Check Boat Status");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\tWrong input. Please enter a number from the list.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Rowing started...");
                    boat.row();
                    break;
                case 2:
                    System.out.println("Stopping rowing...");
                    boat.stopRowing();
                    break;
                case 3:
                    System.out.print("Enter new direction: ");
                    String direction = scanner.nextLine();
                    boat.setDirection(direction);
                    break;
                case 4:
                    System.out.print("Enter distance to travel (km): ");
                    String distanceInput = scanner.nextLine();
                    try {
                        double distance = Double.parseDouble(distanceInput);
                        boat.travel(distance);
                    } catch (NumberFormatException e) {
                        System.out.println("\tInvalid distance. Please enter a valid number.");
                    }
                    break;
                case 5:
                    System.out.print("Enter new hull material: ");
                    String material = scanner.nextLine();
                    boat.changeMaterial(material);
                    break;
                case 6:
                    System.out.print("Enter new number of seats: ");
                    String seatsInput = scanner.nextLine();
                    try {
                        int newSeatCount = Integer.parseInt(seatsInput);
                        if(newSeatCount <= 0){
                            System.out.println("\tNumber of seats must be positive.");
                            break;
                        }
                        boat.rearrangeSeats(newSeatCount);
                    } catch (NumberFormatException e) {
                        System.out.println("\tInvalid number. Please enter a valid integer.");
                    }
                    break;
                case 7:
                    System.out.print("Enter oar synchronization level (0-100): ");
                    String syncInput = scanner.nextLine();
                    try {
                        int syncLevel = Integer.parseInt(syncInput);
                        boat.syncOars(syncLevel);
                    } catch (NumberFormatException e) {
                        System.out.println("\tInvalid input. Please enter a number between 0 and 100.");
                    }
                    break;
                case 8:
                    System.out.print("Enter minutes to rest: ");
                    String restInput = scanner.nextLine();
                    try {
                        int minutes = Integer.parseInt(restInput);
                        if(minutes < 0){
                            System.out.println("\tMinutes cannot be negative.");
                            break;
                        }
                        boat.rest(minutes);
                    } catch (NumberFormatException e) {
                        System.out.println("\tInvalid input. Please enter a valid number.");
                    }
                    break;
                case 9:
                    System.out.println("\n--- Boat Status ---");
                    System.out.println("Direction: " + boat.getDirection());
                    System.out.println("Speed: " + boat.getSpeed() + " km/h");
                    System.out.println("Distance Traveled: " + boat.getDistanceTraveled() + " km");
                    System.out.println("Fatigue Level: " + boat.getFatigue() + "/100");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    boat.dispose();
                    continueApp = false;
                    break;
                default:
                    System.out.println("\tInvalid option. Please select a valid option from the menu.");
            }
        }

        scanner.close();
    }
}
