import java.util.*;

/**
 * UC11_ConcurrentBooking
 *
 * Demonstrates thread-safe booking using synchronization
 */

// Shared Booking Processor
class ConcurrentBookingProcessor {

    private Queue<Reservation> bookingQueue;
    private Inventory inventory;

    public ConcurrentBookingProcessor(Inventory inventory) {
        this.inventory = inventory;
        bookingQueue = new LinkedList<>();
    }

    // Add booking request (shared resource)
    public synchronized void addRequest(Reservation r) {
        bookingQueue.add(r);
        System.out.println("Request Added: " + r.getGuestName());
    }

    // Process booking (critical section)
    public synchronized void processRequest() {

        if (bookingQueue.isEmpty()) return;

        Reservation r = bookingQueue.poll();

        String roomType = r.getRoomType();

        if (inventory.getAvailability(roomType) > 0) {

            inventory.decrease(roomType);

            System.out.println(Thread.currentThread().getName()
                    + " booked " + roomType + " for " + r.getGuestName());

        } else {
            System.out.println("No rooms available for " + roomType);
        }
    }
}

// Thread class
class BookingThread extends Thread {

    private ConcurrentBookingProcessor processor;

    public BookingThread(ConcurrentBookingProcessor processor) {
        this.processor = processor;
    }

    public void run() {

        for (int i = 0; i < 2; i++) {
            processor.processRequest();
        }
    }
}

// Main class
public class UC11_ConcurrentBooking {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        ConcurrentBookingProcessor processor =
                new ConcurrentBookingProcessor(inventory);

        // Add multiple booking requests
        processor.addRequest(new Reservation("Aman", "Single"));
        processor.addRequest(new Reservation("Riya", "Single"));
        processor.addRequest(new Reservation("Rahul", "Single"));
        processor.addRequest(new Reservation("Neha", "Double"));

        // Create multiple threads
        Thread t1 = new BookingThread(processor);
        Thread t2 = new BookingThread(processor);

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();
    }
}