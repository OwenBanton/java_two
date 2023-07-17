// Name: Owen Banton

package javaProjects.$OWEN_BANTON$_A4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class $OWEN_BANTON$_A4 {

    public static void main(String[] args) {

        Hotel hotel = Hotel.getInstance();              // instance of hotel class

        boolean[] alreadyReserved = new boolean[3];     // array to keep track of which rooms are already reserved.


        System.out.println("\n" + "Please enter a your name.");
        Scanner nameScanner = new Scanner(System.in);
        String name = nameScanner.nextLine();
        System.out.println("Welcome to " + name + " hotel maker, please select the room you would like to have in your hotel. \n"); // initial dialog to take user name.

        takeInput(hotel, alreadyReserved);        // call function for user to interact with hotel. Takes arguments for recursive calls.
    }

    public static void takeInput(Hotel hotel, boolean[] alreadyReserved) {

        RoomFactory.RoomType roomType;                                       // creating room type object to be configured by the user.
        while (true) {                                                       // loop to show commands and take user input
            System.out.println("1: Single Room $50");
            System.out.println("2: Double Room $80");
            System.out.println("3: Deluxe Room $100");
            System.out.println("4: Show Hotel Revenue");

            Scanner scanner = new Scanner(System.in);                       // scanner to take input
            String input = scanner.nextLine();
            int command = Integer.parseInt(input);

            if ((command < 4) && (alreadyReserved[command - 1])) {               // check to see if selected room reserved already.
                System.out.println("Sorry, this room is not available.\n");
                continue;
            }

            switch (command) {                                              // declaring the room type based on user input
                case 1:
                    roomType = RoomFactory.RoomType.SINGLE;
                    break;
                case 2:
                    roomType = RoomFactory.RoomType.DOUBLE;
                    break;
                case 3:
                    roomType = RoomFactory.RoomType.DELUXE;
                    break;
                case 4:                                                     // option to display hotel revenue, function to add up the cost of all currently reserved rooms.
                    int revenue = 0;                                        // moved this to the front menu and made room selection go forward instead of having a "go to reservation" option as it was difficult to implement more menus.
                    for (Room room : hotel.getRooms()) {
                        if (room.getReservationStatus()) {
                            revenue += room.getPrice();
                        }
                    }
                    System.out.println("Hotel revenue: $" + revenue + "\n");
                    continue;
                default:
                    System.out.println("Please choose a command between 1-4.");
                    continue;
            }

            Room room = RoomFactory.createRoom(roomType);                   // creating room object based on selected room type.


            while (true) {                                                  // loop to display additional room features and take commands
                System.out.println("What features should the room have?");
                System.out.println("1: With view $50");
                System.out.println("2: With Netflix access $20");
                System.out.println("3: Nothing");
                System.out.println("4: Done, go to reservation");

                String featureInput = scanner.nextLine();
                int featureCommand = Integer.parseInt(featureInput);           // scanner input for features list.

                if (featureCommand == 4) {                                     // option to finalize the reservation
                    if (room != null) {
                        room.reserveTheRoom();
                        hotel.addRoom(room);
                        switch (roomType) {
                            case SINGLE:
                                alreadyReserved[0] = true;
                            case DOUBLE:
                                alreadyReserved[1] = true;
                            case DELUXE:
                                alreadyReserved[2] = true;
                        }
                        for (Room roomListed : hotel.getRooms()) {         // prints current list of rooms once reservation is complete. Only prints reserved rooms currently as rooms are added to the list during the reservation process.
                            System.out.println("Type: " + roomListed.getDescription() + ", Price: " + roomListed.getPrice() + ", Reserved: " + roomListed.getReservationStatus());
                            System.out.println();
                        }

                        takeInput(hotel, alreadyReserved);                  // returns to the top of the function after a reservation is completed.
                    }
                }

                switch (featureCommand) {                                   // switch menu to add features based on command, contains conditions to prevent selecting same feature twice.
                    case 1:
                        if (room.getDescription().contains("View")) {
                            System.out.println("You already have this feature.");
                            continue;
                        } else {
                            room = new WithView(room);
                        }
                        continue;
                    case 2:
                        if (room.getDescription().contains("Netflix")) {
                            System.out.println("You already have this feature.");

                        } else {
                            room = new WithNetflixAccess(room);
                        }
                        continue;
                    case 3:
                        continue;
                    default:
                        System.out.println("Please choose a command between 1-4.");
                }
            }


        }
    }

}

// Singleton hotel class containing list of rooms and simple functions described in the uml.
class Hotel {

    private static Hotel instance;
    private List<Room> rooms;

    private Hotel() {
        rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean reserveRoom(int room) {
        rooms.get(room).reserveTheRoom();
        return true;
    }

    public int getRevenue() {                                      // Simple loop function to determine total revenue from reserved rooms.
        int revenue = 0;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getReservationStatus()) {
                revenue += rooms.get(i).getPrice();
            }
        }
        return revenue;
    }

    public static Hotel getInstance() {                             // singleton instance function to allow only a single instance in the program.
        if (instance == null) {
            instance = new Hotel();
        }
        return instance;
    }

}

// room interface with suggested attributes/functions.
interface Room {

    public int getPrice();

    public String getDescription();

    public boolean getReservationStatus();

    public void reserveTheRoom();

}

// Single room class with the indicated price and descriptions, and functions from the Room interface.
class SingleRoom implements Room {

    private boolean reservationStatus = false;
    private int price = 50;
    private String description = "Single Room";

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void reserveTheRoom() {
        reservationStatus = true;
    }

    @Override
    public boolean getReservationStatus() {
        return reservationStatus;
    }


}

// Double room class with the indicated price and descriptions, and functions from the Room interface.
class DoubleRoom implements Room {

    private boolean reservationStatus = false;
    private int price = 80;
    private String description = "Double Room";

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void reserveTheRoom() {
        reservationStatus = true;
    }

    @Override
    public boolean getReservationStatus() {
        return reservationStatus;
    }

}

// Deluxe room class with the indicated price and descriptions, and functions from the Room interface.
class DeluxeRoom implements Room {

    private boolean reservationStatus = false;
    private int price = 100;
    private String description = "Deluxe Room";

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void reserveTheRoom() {
        reservationStatus = true;
    }

    @Override
    public boolean getReservationStatus() {
        return reservationStatus;
    }

}

// Room factory class with function to generate rooms based on type.
class RoomFactory {

    public enum RoomType {SINGLE, DOUBLE, DELUXE}       // room types declared by enum value.

    public static Room createRoom(RoomType roomType) {
        switch (roomType) {
            case SINGLE:
                return new SingleRoom();
            case DOUBLE:
                return new DoubleRoom();
            case DELUXE:
                return new DeluxeRoom();
            default:
                return null;
        }

    }
}

// room decorator class that implements the room class and returns decorated versions of room objects.
class RoomDecorator implements Room {

    public Room decoratedRoom;

    public RoomDecorator(Room decoratedRoom) {
        this.decoratedRoom = decoratedRoom;
    }

    @Override
    public int getPrice() {
        return decoratedRoom.getPrice();
    }

    @Override
    public String getDescription() {
        return decoratedRoom.getDescription();
    }

    @Override
    public boolean getReservationStatus() {
        return decoratedRoom.getReservationStatus();
    }

    @Override
    public void reserveTheRoom() {
        decoratedRoom.reserveTheRoom();
    }
}

// The view decorator class, which updates and returns room objects with description and price matching the view feature.
class WithView extends RoomDecorator {

    public WithView(Room decoratedRoom) {
        super(decoratedRoom);
    }

    @Override
    public int getPrice() {
        return decoratedRoom.getPrice() + 50;
    }

    @Override
    public String getDescription() {
        return decoratedRoom.getDescription() + " with view";
    }

}

// The netflix decorator class, which updates and returns room objects with description and price matching the netflix feature.
class WithNetflixAccess extends RoomDecorator {

    public WithNetflixAccess(Room decoratedRoom) {
        super(decoratedRoom);
    }

    @Override
    public int getPrice() {
        return decoratedRoom.getPrice() + 20;
    }

    @Override
    public String getDescription() {
        return decoratedRoom.getDescription() + " with Netflix access";
    }

}





