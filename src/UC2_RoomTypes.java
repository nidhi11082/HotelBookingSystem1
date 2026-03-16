/**
 * UC2_RoomTypes
 *
 * Demonstrates room modeling using abstraction and inheritance.
 * Room types are created and static availability is displayed.
 *
 * @author Nidhi
 * @version 1.0
 */

// Abstract Room class
abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    // Method to display room details
    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price per night: $" + price);
    }
}

// Single Room class
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 100);
    }
}

// Double Room class
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 180);
    }
}

// Suite Room class
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 350);
    }
}

public class UC2_RoomTypes {

    public static void main(String[] args) {

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display room details
        System.out.println("=== Room Types & Availability ===");

        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}