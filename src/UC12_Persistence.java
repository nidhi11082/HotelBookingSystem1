import java.io.*;
import java.util.*;

/**
 * UC12_Persistence
 *
 * Demonstrates saving and loading system state using serialization
 */

// Wrapper class for storing system state
class SystemState implements Serializable {

    private HashMap<String, Integer> inventoryData;
    private List<Reservation> bookings;

    public SystemState(HashMap<String, Integer> inventoryData,
                       List<Reservation> bookings) {
        this.inventoryData = inventoryData;
        this.bookings = bookings;
    }

    public HashMap<String, Integer> getInventoryData() {
        return inventoryData;
    }

    public List<Reservation> getBookings() {
        return bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    // Save state
    public static void save(SystemState state) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load state
    public static SystemState load() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            SystemState state = (SystemState) ois.readObject();
            System.out.println("Data loaded successfully.");
            return state;

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

// Main class
public class UC12_Persistence {

    public static void main(String[] args) {

        // Try loading old data
        SystemState loadedState = PersistenceService.load();

        Inventory inventory = new Inventory();
        List<Reservation> bookings = new ArrayList<>();

        if (loadedState != null) {

            // Restore inventory
            for (String key : loadedState.getInventoryData().keySet()) {
                inventory.increase(key); // reset logic
            }

            bookings = loadedState.getBookings();

            System.out.println("Recovered Bookings:");
            for (Reservation r : bookings) {
                System.out.println(r.getGuestName() + " - " + r.getRoomType());
            }

        } else {

            // Fresh run → create data
            bookings.add(new Reservation("Aman", "Single"));
            bookings.add(new Reservation("Riya", "Double"));

            inventory.decrease("Single");
            inventory.decrease("Double");
        }

        // Prepare data to save
        HashMap<String, Integer> data = new HashMap<>();
        data.put("Single", inventory.getAvailability("Single"));
        data.put("Double", inventory.getAvailability("Double"));
        data.put("Suite", inventory.getAvailability("Suite"));

        SystemState state = new SystemState(data, bookings);

        // Save before exit
        PersistenceService.save(state);
    }
}