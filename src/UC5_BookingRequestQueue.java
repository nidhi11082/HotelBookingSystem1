import java.util.LinkedList;
import java.util.Queue;

/**
 * UC5_BookingRequestQueue
 *
 * Demonstrates how booking requests are collected
 * and stored using a Queue to preserve arrival order.
 *
 * @author Nidhi
 * @version 1.0
 */

// Reservation object
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayRequest() {
        System.out.println("Guest: " + guestName + " requested " + roomType);
    }
}

public class UC5_BookingRequestQueue {

    public static void main(String[] args) {

        // Queue to store booking requests
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Guests submit booking requests
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));

        System.out.println("=== Booking Requests in Queue ===");

        // Display requests in arrival order
        for (Reservation request : bookingQueue) {
            request.displayRequest();
        }

        System.out.println("\nRequests are waiting for processing...");
    }
}