public class WaterTank {

    private double capacity;
    private double currentLevel;


    // ask user to create its own capacity
    public WaterTank(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.currentLevel = 0;
    }

    // add ug tubig
    public void fill(double liters) {
        if (liters <= 0) {
            System.out.println("Invalid amount, numbers should always be positive.");
            return;
        }

        currentLevel += liters;
        if (currentLevel > capacity) {
            currentLevel = capacity;
            System.out.println("Tank is full, Some water overflowed... Current water level: " + currentLevel);
        } else {
            System.out.println(liters + " liters added.");
        }
    }

    // remove ug tubig
    public void drain(double liters) {
        if (liters <= 0) {
            System.out.println("Invalid amount, numbers should always be positive.");
            return;
        }

        currentLevel -= liters;
        if (currentLevel < 0) {
            currentLevel = 0;
            System.out.println("Tank is empty, unable to drain more.");
        } else {
            System.out.println(liters + " liters drained.");
        }
    }

    // display current level niya
    public double getCurrentLevel() {
        return currentLevel;
    }

    public boolean isFull() {
        return currentLevel == capacity;
    }

    public boolean isEmpty() {
        return currentLevel == 0;
    }

    public void displayTank() {
        int height = 10;
        int filledRows = (int) Math.round((currentLevel / capacity) * height);


        System.out.println("        ╔════════╗");
        for (int i = height; i > 0; i--) {
            if (i <= filledRows) {
                if (i == filledRows) {
                    System.out.println("        ║~~~~~~~~║");
                } else {
                    System.out.println("        ║████████║");
                }
            } else {
                System.out.println("        ║        ║");
            }
        }
        System.out.println("        ╚════════╝"); 

        double percent = (currentLevel / capacity) * 100;
        System.out.printf("Capacity: %.1f Liters | Current Level: %.1f Liters (%.1f%%)\n",
                capacity, currentLevel, percent);
    }

}
