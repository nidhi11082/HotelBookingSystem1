/**
 * UC4_RoomSearch
 * Read-only search of available rooms
 */

class RoomInfo {

    private String type;
    private int price;

    public RoomInfo(String type, int price) {
        this.type = type;
        this.price = price;
    }

    public void displayDetails(int availability) {
        System.out.println(type + " | Price: " + price + " | Available: " + availability);
    }

    public String getType() {
        return type;
    }
}

public class UC4_RoomSearch {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        RoomInfo r1 = new RoomInfo("Single", 1000);
        RoomInfo r2 = new RoomInfo("Double", 2000);
        RoomInfo r3 = new RoomInfo("Suite", 5000);

        System.out.println("Available Rooms:");

        if (inventory.getAvailability("Single") > 0)
            r1.displayDetails(inventory.getAvailability("Single"));

        if (inventory.getAvailability("Double") > 0)
            r2.displayDetails(inventory.getAvailability("Double"));

        if (inventory.getAvailability("Suite") > 0)
            r3.displayDetails(inventory.getAvailability("Suite"));
    }
}