import java.util.*;

/**
 * UC3_RoomInventory
 * Centralized inventory using HashMap
 */

class Inventory {

    private HashMap<String, Integer> roomAvailability;

    public Inventory() {
        roomAvailability = new HashMap<>();

        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 2);
        roomAvailability.put("Suite", 1);
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public void decrease(String roomType) {
        roomAvailability.put(roomType, getAvailability(roomType) - 1);
    }

    public void increase(String roomType) {
        roomAvailability.put(roomType, getAvailability(roomType) + 1);
    }

    public void display() {
        System.out.println("Inventory: " + roomAvailability);
    }
}

public class UC3_RoomInventory {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.display();
    }
}