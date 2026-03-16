import java.util.HashMap;
import java.util.Map;

/**
 * UC3_RoomInventory
 *
 * Demonstrates centralized room inventory management using HashMap.
 * All room availability is stored in a single data structure.
 *
 * @author Nidhi
 * @version 1.0
 */

class RoomInventory {

    // Centralized inventory storage
    private HashMap<String, Integer> inventory;

    // Constructor initializes room availability
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Retrieve availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display entire inventory
    public void displayInventory() {

        System.out.println("=== Current Room Inventory ===");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " available");
        }
    }
}

public class UC3_RoomInventory {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        // Example update
        System.out.println("\nBooking one Single Room...");

        int current = inventory.getAvailability("Single Room");
        inventory.updateAvailability("Single Room", current - 1);

        // Display updated inventory
        inventory.displayInventory();
    }
}