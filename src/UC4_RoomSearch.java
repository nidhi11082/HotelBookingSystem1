import java.util.HashMap;
import java.util.Map;

/**
 * UC4_RoomSearch
 *
 * Demonstrates read-only room search functionality.
 * Guests can view available room types without modifying system state.
 *
 * @author Nidhi
 * @version 1.0
 */

// Simple room model
class RoomInfo {

    private String type;
    private int beds;
    private double price;

    public RoomInfo(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: $" + price);
    }

    public String getType() {
        return type;
    }
}

// Inventory class (read-only access)
class Inventory {

    private HashMap<String, Integer> roomAvailability;

    public Inventory() {

        roomAvailability = new HashMap<>();

        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Suite Room", 0); // unavailable
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public HashMap<String, Integer> getInventory() {
        return roomAvailability;
    }
}

public class UC4_RoomSearch {

    public static void main(String[] args) {

        // Initialize inventory
        Inventory inventory = new Inventory();

        // Room domain objects
        RoomInfo single = new RoomInfo("Single Room", 1, 100);
        RoomInfo doubleRoom = new RoomInfo("Double Room", 2, 180);
        RoomInfo suite = new RoomInfo("Suite Room", 3, 350);

        System.out.println("=== Available Rooms ===");

        // Check availability without modifying state
        for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {

            String type = entry.getKey();
            int available = entry.getValue();

            // Filter unavailable rooms
            if (available > 0) {

                if (type.equals("Single Room")) {
                    single.displayDetails();
                }
                else if (type.equals("Double Room")) {
                    doubleRoom.displayDetails();
                }
                else if (type.equals("Suite Room")) {
                    suite.displayDetails();
                }

                System.out.println("Available Rooms: " + available);
                System.out.println();
            }
        }
    }
}
