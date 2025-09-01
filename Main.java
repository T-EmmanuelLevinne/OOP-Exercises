import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
   Scanner console = new Scanner(System.in);

   // starter capacity
        System.out.println("Enter water tank capacity (liters): ");
        double capacity = console.nextDouble();
        WaterTank tank = new WaterTank(capacity);

        boolean nagRun = true;
        while (nagRun) {
            System.out.println("\n === Water Tank ===");
            System.out.println("1. Fill tank");
            System.out.println("2. Drain Tank");
            System.out.println("3. Check current water level");
            System.out.println("4. Display visualization"); // temporary kay lisod ni bcuz mag gamit ug for loops
            System.out.println("5. Check if tank is Empty");
            System.out.println("6. Check if tank is Full");
            System.out.println("7. Exit");

            System.out.println("Enter your choice ( 1 to 7): ");

            int choice = console.nextInt();
            if (choice < 1 || choice > 7) {
                System.out.println("Invalid choice");
            } else {
                System.out.println("Invalid Input!");
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter liters to add: ");
                    double add = console.nextDouble();
                    tank.addWater(add);
                    break;

                case 2:
                    System.out.println("7. Enter liters to drain: ");
                    double drainWater = console.nextDouble();
                    tank.removeWater(drainWater);
                    break;

                case 3:
                    System.out.println("Current water leveL: " + tank.getCurrentLevel());
                    break;

                case 4:
                    tank.displayTank();

                case 5:
                    if (tank.isEmpty()) {
                        System.out.println("Tank is Empty");
                    } else {
                        System.out.println("Tank is still at: " + tank.getCurrentLevel() +" liters and not empty.");
                    }
                    break;

                case 6:
                    if (tank.isFull()) {
                        System.out.println("Tank is Full");
                    } else {
                        System.out.println("The tank is not full, current water level: " + tank.getCurrentLevel());
                    }
                    break;

                case 7:
                    System.out.println("Exiting... babye.. :c");
                    nagRun = false;
                    break;

                    default:
                        System.out.println("Invalid choice! try again");
            }
        }

    }
}
