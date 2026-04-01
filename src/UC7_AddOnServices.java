import java.util.*;

/**
 * UC7_AddOnServices
 *
 * Demonstrates adding optional services to a reservation
 * without modifying booking or inventory logic.
 */

class Service {

    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Manages add-on services
class AddOnServiceManager {

    // Map: Reservation ID → List of Services
    private Map<String, List<Service>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.getPrice();
            }
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Services for Reservation: " + reservationId);

        for (Service s : services) {
            System.out.println("- " + s.getName() + " ($" + s.getPrice() + ")");
        }
    }
}

public class UC7_AddOnServices {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        // Example reservation (from UC6)
        String reservationId = "SingleRoom-1";

        // Guest selects services
        manager.addService(reservationId, new Service("Breakfast", 20));
        manager.addService(reservationId, new Service("Spa", 50));
        manager.addService(reservationId, new Service("Airport Pickup", 30));

        // Display selected services
        manager.displayServices(reservationId);

        // Calculate cost
        double total = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-On Cost: $" + total);
    }
}