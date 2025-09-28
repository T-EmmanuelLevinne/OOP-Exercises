// Base class
class Shipment {
    private String trackingNumber;
    private String sender;
    private String receiver;
    private double weight;
    private double baseRate;
    private double distanceKm;
    private boolean delivered;

    public Shipment(String trackingNumber, String sender, String receiver,
                    double weight, double baseRate, double distanceKm) {
        this.trackingNumber = trackingNumber;
        this.sender = sender;
        this.receiver = receiver;
        setWeight(weight);
        setBaseRate(baseRate);
        setDistanceKm(distanceKm);
        this.delivered = false;
    }

    // calculate cost
    public double calculateCost() {
        return weight * baseRate;
    }

    // mark as delivered
    public void markDelivered() {
        delivered = true;
    }

    public String getDeliveryStatus() {
        return delivered ? "Delivered" : "In Transit";
    }

    public int getEstimatedDeliveryDays() {
        double days = distanceKm / 500.0; // default for Standard
        return (int) Math.ceil(days);
    }

    public String getDetails() {
        return "Tracking: " + trackingNumber +
               "\nSender: " + sender +
               "\nReceiver: " + receiver +
               "\nWeight: " + weight + " kg" +
               "\nBase Rate: " + baseRate +
               "\nDistance: " + distanceKm + " km" +
               "\nCost: " + calculateCost() +
               "\nStatus: " + getDeliveryStatus();
    }

    // getters and setters with validation
    public double getWeight() { return weight; }
    public void setWeight(double weight) {
        if (weight <= 0) throw new IllegalArgumentException("Weight must be positive");
        this.weight = weight;
    }

    public double getBaseRate() { return baseRate; }
    public void setBaseRate(double baseRate) {
        if (baseRate <= 0) throw new IllegalArgumentException("Base rate must be positive");
        this.baseRate = baseRate;
    }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) {
        if (distanceKm <= 0) throw new IllegalArgumentException("Distance must be positive");
        this.distanceKm = distanceKm;
    }
}

// StandardShipment
class StandardShipment extends Shipment {
    private double insuranceFee;

    public StandardShipment(String trackingNumber, String sender, String receiver,
                            double weight, double baseRate, double distanceKm,
                            double insuranceFee) {
        super(trackingNumber, sender, receiver, weight, baseRate, distanceKm);
        this.insuranceFee = insuranceFee;
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() + insuranceFee;
    }

    @Override
    public String getDetails() {
        return super.getDetails() +
               "\nInsurance Fee: " + insuranceFee;
    }

    @Override
    public int getEstimatedDeliveryDays() {
        double days = getDistanceKm() / 500.0;
        return (int) Math.ceil(days);
    }
}

// ExpressShipment
class ExpressShipment extends Shipment {
    private String priorityLevel;
    private double priorityFee;

    public ExpressShipment(String trackingNumber, String sender, String receiver,
                           double weight, double baseRate, double distanceKm,
                           String priorityLevel) {
        super(trackingNumber, sender, receiver, weight, baseRate, distanceKm);
        this.priorityLevel = priorityLevel;
        setPriorityFee(priorityLevel);
    }

    private void setPriorityFee(String priorityLevel) {
        switch (priorityLevel) {
            case "High": priorityFee = 200; break;
            case "Rush": priorityFee = 500; break;
            default: priorityFee = 0; break;
        }
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() + priorityFee;
    }

    public boolean isHighPriority() {
        return priorityLevel.equals("High") || priorityLevel.equals("Rush");
    }

    @Override
    public int getEstimatedDeliveryDays() {
        double days = getDistanceKm() / 800.0;
        return (int) Math.ceil(days);
    }

    @Override
    public String getDetails() {
        return super.getDetails() +
               "\nPriority: " + priorityLevel +
               "\nPriority Fee: " + priorityFee;
    }
}

// InternationalShipment
class InternationalShipment extends Shipment {
    private double customsFee;
    private String destinationCountry;

    public InternationalShipment(String trackingNumber, String sender, String receiver,
                                 double weight, double baseRate, double distanceKm,
                                 double customsFee, String destinationCountry) {
        super(trackingNumber, sender, receiver, weight, baseRate, distanceKm);
        this.customsFee = customsFee;
        this.destinationCountry = destinationCountry;
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() + customsFee;
    }

    public boolean requiresCustomsDeclaration() {
        return getWeight() > 2.0;
    }

    @Override
    public int getEstimatedDeliveryDays() {
        double days = getDistanceKm() / 400.0;
        return (int) Math.ceil(days) + 2; // customs delay
    }

    @Override
    public String getDetails() {
        return super.getDetails() +
               "\nCustoms Fee: " + customsFee +
               "\nDestination Country: " + destinationCountry;
    }
}

// Test Program
public class ShippingSystemTest {
    public static void main(String[] args) {
        // Standard
        StandardShipment standard = new StandardShipment("STD123", "Alice", "Bob",
                2.5, 50, 1000, 100);
        System.out.println("=== Standard Shipment ===");
        System.out.println(standard.getDetails());
        System.out.println("Estimated Days: " + standard.getEstimatedDeliveryDays());
        System.out.println("Before Delivery: " + standard.getDeliveryStatus());
        standard.markDelivered();
        System.out.println("After Delivery: " + standard.getDeliveryStatus());
        System.out.println();

        // Express
        ExpressShipment express = new ExpressShipment("EXP456", "Carol", "Dave",
                5, 70, 1600, "Rush");
        System.out.println("=== Express Shipment ===");
        System.out.println(express.getDetails());
        System.out.println("Estimated Days: " + express.getEstimatedDeliveryDays());
        System.out.println("High Priority? " + express.isHighPriority());
        System.out.println();

        // International
        InternationalShipment intl = new InternationalShipment("INT789", "Eve", "Frank",
                3.2, 80, 2000, 300, "Japan");
        System.out.println("=== International Shipment ===");
        System.out.println(intl.getDetails());
        System.out.println("Estimated Days: " + intl.getEstimatedDeliveryDays());
        System.out.println("Requires Customs Declaration? " + intl.requiresCustomsDeclaration());
    }
}
