import java.util.*;

/**
 * UC10_Cancellation
 * Cancellation with rollback using Stack
 */

class CancellationService {

    private Map<String, String> activeBookings;
    private Stack<String> rollbackStack;
    private Inventory inventory;

    public CancellationService(Inventory inventory) {
        this.inventory = inventory;
        activeBookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    public void bookRoom(String reservationId, String roomType) {

        if (inventory.getAvailability(roomType) <= 0) {
            System.out.println("No rooms available for " + roomType);
            return;
        }

        inventory.decrease(roomType);
        activeBookings.put(reservationId, roomType);

        System.out.println("Booked: " + reservationId + " → " + roomType);
    }

    public void cancelBooking(String reservationId) {

        if (!activeBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Booking not found!");
            return;
        }

        String roomType = activeBookings.get(reservationId);

        rollbackStack.push(reservationId);

        inventory.increase(roomType);

        activeBookings.remove(reservationId);

        System.out.println("Cancelled: " + reservationId);
    }

    public void showRollback() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

public class UC10_Cancellation {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        CancellationService service = new CancellationService(inventory);

        service.bookRoom("R101", "Single");
        service.bookRoom("R102", "Double");

        inventory.display();

        service.cancelBooking("R101");

        inventory.display();

        service.cancelBooking("R999");

        service.showRollback();
    }
}