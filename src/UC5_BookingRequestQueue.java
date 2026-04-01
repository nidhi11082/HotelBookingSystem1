import java.util.*;

/**
 * UC5_BookingRequestQueue
 *
 * Demonstrates booking request handling using Queue (FIFO)
 */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayRequest() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

public class UC5_BookingRequestQueue {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Add booking requests
        bookingQueue.add(new Reservation("Aman", "Single"));
        bookingQueue.add(new Reservation("Riya", "Double"));
        bookingQueue.add(new Reservation("Rahul", "Suite"));

        System.out.println("Booking Requests in Queue:");

        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();
            r.displayRequest();
        }
    }
}