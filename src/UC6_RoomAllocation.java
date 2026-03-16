import java.util.*;

/**
 * UC6_RoomAllocation
 *
 * Demonstrates safe reservation confirmation and room allocation.
 * Prevents double booking using Set and maintains inventory consistency.
 */

class ReservationRequest {

    String guestName;
    String roomType;

    public ReservationRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingService {

    // Inventory (room type → available count)
    private HashMap<String, Integer> inventory;

    // Allocated room IDs per room type
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService() {

        inventory = new HashMap<>();
        allocatedRooms = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);

        allocatedRooms.put("Single Room", new HashSet<>());
        allocatedRooms.put("Double Room", new HashSet<>());
        allocatedRooms.put("Suite Room", new HashSet<>());
    }

    // Process booking request
    public void allocateRoom(ReservationRequest request) {

        String roomType = request.roomType;

        int available = inventory.getOrDefault(roomType, 0);

        if (available <= 0) {
            System.out.println("No rooms available for " + roomType);
            return;
        }

        // Generate unique room ID
        String roomId = roomType.replace(" ", "") + "-" + (available);

        // Check uniqueness
        Set<String> assigned = allocatedRooms.get(roomType);

        if (assigned.contains(roomId)) {
            System.out.println("Room ID already allocated!");
            return;
        }

        // Allocate room
        assigned.add(roomId);

        // Update inventory
        inventory.put(roomType, available - 1);

        System.out.println("Reservation confirmed for " + request.guestName);
        System.out.println("Assigned Room ID: " + roomId);
        System.out.println();
    }
}

public class UC6_RoomAllocation {

    public static void main(String[] args) {

        // Booking request queue (FIFO)
        Queue<ReservationRequest> bookingQueue = new LinkedList<>();

        bookingQueue.add(new ReservationRequest("Alice", "Single Room"));
        bookingQueue.add(new ReservationRequest("Bob", "Double Room"));
        bookingQueue.add(new ReservationRequest("Charlie", "Suite Room"));

        BookingService bookingService = new BookingService();

        System.out.println("=== Processing Booking Requests ===\n");

        // Process requests
        while (!bookingQueue.isEmpty()) {

            ReservationRequest request = bookingQueue.poll();

            bookingService.allocateRoom(request);
        }
    }
}