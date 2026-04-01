import java.util.*;

/**
 * UC8_BookingHistory
 *
 * Demonstrates storing confirmed bookings and generating reports
 */

// Booking History (stores data)
class BookingHistory {

    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}

// Report Service (read-only)
class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {

        System.out.println("Booking History:");

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName()
                    + " | Room Type: " + r.getRoomType());
        }
    }

    public void generateSummary(List<Reservation> reservations) {

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : reservations) {
            summary.put(r.getRoomType(),
                    summary.getOrDefault(r.getRoomType(), 0) + 1);
        }

        System.out.println("\nBooking Summary:");

        for (String type : summary.keySet()) {
            System.out.println(type + " → " + summary.get(type));
        }
    }
}

public class UC8_BookingHistory {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Using SAME Reservation class from UC5
        history.addReservation(new Reservation("Aman", "Single"));
        history.addReservation(new Reservation("Riya", "Double"));
        history.addReservation(new Reservation("Rahul", "Single"));
        history.addReservation(new Reservation("Neha", "Suite"));

        reportService.displayAllBookings(history.getAllReservations());
        reportService.generateSummary(history.getAllReservations());
    }
}