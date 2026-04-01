import java.util.*;

/**
 * UC9_ErrorHandling
 *
 * Demonstrates validation and custom exception handling
 */

// Custom Exception
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator class
class BookingValidator {

    private static final List<String> VALID_ROOM_TYPES =
            Arrays.asList("Single", "Double", "Suite");

    // Validate booking
    public static void validate(String guestName, String roomType, int availableRooms)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!VALID_ROOM_TYPES.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }
}

public class UC9_ErrorHandling {

    public static void main(String[] args) {

        // Example inputs
        String guestName = "Aman";
        String roomType = "Single";
        int availableRooms = 2;

        try {
            // Validate before booking
            BookingValidator.validate(guestName, roomType, availableRooms);

            // If valid → proceed
            System.out.println("Booking successful for " + guestName +
                    " in " + roomType + " room.");

        } catch (InvalidBookingException e) {

            // Graceful error handling
            System.out.println("Booking Failed: " + e.getMessage());
        }

        // Try invalid case (to demonstrate error)
        try {
            BookingValidator.validate("", "Luxury", 0);
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}