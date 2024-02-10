//package hotelreservation;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//
//class User {
//    private String username;
//    private String password;
//
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//    // Getters and setters for username and password
//
//    void setUsername(String username){
//        this.username=username;
//    }
//    String getUsername(){
//        return username;
//    }
//    void setPassword(String password){
//        this.password=password;
//    }
//    String getPassword(){
//        return password;
//    }
//
//}
//
//class Room {
//    private int roomNumber;
//    private String roomType;
//    private double price;
//    private boolean isAvailable;
//
//    public Room(int roomNumber, String roomType, double price) {
//        this.roomNumber = roomNumber;
//        this.roomType = roomType;
//        this.price = price;
//        this.isAvailable = true;
//    }
//
//    // Getters and setters for room details
//
//    public void setAvailable(boolean available) {
//        isAvailable = available;
//    }
//
//    public boolean getIsAvailable() {
//        return isAvailable;
//    }
//
//    public void setRoomNumber(int roomNumber) {
//        this.roomNumber = roomNumber;
//    }
//
//    public void setRoomType(String roomType) {
//        this.roomType = roomType;
//    }
//
//    public String getRoomType() {
//        return roomType;
//    }
//
//    public int getRoomNumber() {
//        return roomNumber;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//}
//
//class Reservation {
//    private int reservationId;
//    private User user;
//    private Room room;
//    private Date checkInDate;
//    private Date checkOutDate;
//
//    public Reservation(int reservationId, User user, Room room, Date checkInDate, Date checkOutDate) {
//        this.reservationId = reservationId;
//        this.user = user;
//        this.room = room;
//        this.checkInDate = checkInDate;
//        this.checkOutDate = checkOutDate;
//    }
//
//    // Getters for reservation details
//
//    public void setReservationId(int reservationId) {
//        this.reservationId = reservationId;
//    }
//
//    public int getReservationId() {
//        return reservationId;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public Room getRoom() {
//        return room;
//    }
//
//    public void setCheckInDate(Date checkInDate) {
//        this.checkInDate = checkInDate;
//    }
//
//    public Date getCheckInDate() {
//        return checkInDate;
//    }
//
//    public void setCheckOutDate(Date checkOutDate) {
//        this.checkOutDate = checkOutDate;
//    }
//
//    public Date getCheckOutDate() {
//        return checkOutDate;
//    }
//}
//
//class Hotel {
//    private List<Room> rooms;
//    private List<Reservation> reservations;
//    private int reservationIdCounter = 1;
//
//    public Hotel() {
//        this.rooms = new ArrayList<>();
//        this.reservations = new ArrayList<>();
//        initializeRooms();
//    }
//
//    private void initializeRooms() {
//        // Add rooms to the hotel
//        rooms.add(new Room(101, "Single", 100.0));
//        rooms.add(new Room(102, "Double", 150.0));
//        rooms.add(new Room(201, "Suite", 200.0));
//        // Add more rooms as needed
//    }
//
//
//        // Implement logic to check room availability based on dates and room type
//        public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, String roomType) {
//            List<Room> availableRooms = new ArrayList<>();
//            for (Room room : rooms) {
//                if (room.getRoomType().equalsIgnoreCase(roomType) && room.getIsAvailable()) {
//                    // Check if the room is available for the specified dates
//                    if (isRoomAvailableForDates(room, checkInDate, checkOutDate)) {
//                        availableRooms.add(room);
//                    }
//                }
//            }
//            // Return a list of available rooms
//            return availableRooms;
//        }
//
//
//    private boolean isRoomAvailableForDates(Room room, Date checkInDate, Date checkOutDate) {
//        // Check if the room is available for the specified dates
//        for (Reservation reservation : reservations) {
//            if (reservation.getRoom().equals(room)) {
//                if (!(checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate()))) {
//                    // Room is not available for the specified dates
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//    public Reservation makeReservation(User user, Room room, Date checkInDate, Date checkOutDate) {
//        if (room.getIsAvailable()) {
//            Reservation reservation = new Reservation(reservationIdCounter++, user, room, checkInDate, checkOutDate);
//            reservations.add(reservation);
//            room.setAvailable(false);
//            return reservation;
//        } else {
//            System.out.println("Room " + room.getRoomNumber() + " is not available for the specified dates.");
//            return null;
//        }
//    }
//
//    public void displayReservationDetails(Reservation reservation) {
//        System.out.println("Reservation Details:");
//        System.out.println("Reservation ID: " + reservation.getReservationId());
//        System.out.println("User: " + reservation.getUser().getUsername());
//        System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
//        System.out.println("Room Type: " + reservation.getRoom().getRoomType());
//        System.out.println("Check-In Date: " + reservation.getCheckInDate());
//        System.out.println("Check-Out Date: " + reservation.getCheckOutDate());
//        System.out.println("Total Price: " + calculateTotalPrice(reservation));
//    }
//
//    private double calculateTotalPrice(Reservation reservation) {
//        long diffInDays = (reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime()) / (24 * 60 * 60 * 1000);
//        return diffInDays * reservation.getRoom().getPrice();
//    }
//
//    public void cancelReservation(Reservation reservation) {
//        // Remove reservation from the list
//        reservations.remove(reservation);
//        // Set the room as available again
//        reservation.getRoom().setAvailable(true);
//        System.out.println("Reservation canceled successfully.");
//    }
//
//    public List<Reservation> getAllReservations() {
//        // Return a list of all reservations
//        return reservations;
//    }
//
////    // Other methods for managing rooms and reservations
//}
//////    public Reservation makeReservation(User user, Room room, Date checkInDate, Date checkOutDate) {
//////        // Check if the room is available
//////        // Create a new reservation and add it to the list
//////        Reservation reservation = new Reservation(reservationIdCounter++, user, room, checkInDate, checkOutDate);
//////        reservations.add(reservation);
//////        room.setAvailable(false);
//////        return reservation;
//////    }
//////
//////    public void displayReservationDetails(Reservation reservation) {
//////        // Display reservation details
//////    }
//////
//////    // Other methods for managing rooms and reservations
////}
//
//public class HotelReservationSystem {
//    public static void main(String[] args) {
//        // Sample code to demonstrate the basic functionality
//        Hotel hotel = new Hotel();
//        User user=new User("Muskan","123");
//        Room room=new Room(102,"Single",100.0);
//        Reservation reservation=new Reservation(1,user,room,",10-30-2023);
//
//        // Sample user
//        Scanner scanner = new Scanner(System.in);
//
//        int choice;
//        do {
//            System.out.println("Hotel Reservation System Menu:");
//            System.out.println("1. Make Reservation");
//            System.out.println("2. Cancel Reservation");
//            System.out.println("3. Display All Reservations");
//            System.out.println("4. Exit");
//            System.out.print("Enter your choice: ");
//
//            choice = scanner.nextInt();
//            scanner.nextLine(); // consume the newline character
//
//            switch (choice) {
//                case 1:
//                    hotel.makeReservation(user,room,d);
//                    break;
//                case 2:
//                    hotel.cancelReservation(hotel, scanner);
//                    break;
//                case 3:
//                    hotel.displayReservationDetails(hotel);
//                    break;
//                case 4:
//                    System.out.println("Exiting Hotel Reservation System. Goodbye!");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please enter a valid option.");
//            }
//        } while (choice != 4);
//
//        scanner.close();
//
//
//        //User user = new User("john_doe", "password");
//
//        // Sample reservation
//        List<Room> availableRooms = hotel.getAvailableRooms(new Date(), new Date(), "Single");
//        if (!availableRooms.isEmpty()) {
//            Room selectedRoom = availableRooms.get(0);
//            Reservation reservation = hotel.makeReservation(user, selectedRoom, new Date(), new Date());
//            hotel.displayReservationDetails(reservation);
//        } else {
//            System.out.println("No available rooms found.");
//        }
//    }
//
//
//}
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters for username and password

    void setUsername(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getPassword() {
        return password;
    }

}

class Room {
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;
    }

    // Getters and setters for room details

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class Reservation {
    private int reservationId;
    private User user;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(int reservationId, User user, Room room, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.user = user;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters for reservation details

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int reservationIdCounter = 1;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        // Add rooms to the hotel
        rooms.add(new Room(101, "Single", 100.0));
        rooms.add(new Room(102, "Double", 150.0));
        rooms.add(new Room(201, "Suite", 200.0));
        // Add more rooms as needed
    }

    // Implement logic to check room availability based on dates and room type
    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, String roomType) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && room.getIsAvailable()) {
                // Check if the room is available for the specified dates
                if (isRoomAvailableForDates(room, checkInDate, checkOutDate)) {
                    availableRooms.add(room);
                }
            }
        }
        // Return a list of available rooms
        return availableRooms;
    }

    private boolean isRoomAvailableForDates(Room room, Date checkInDate, Date checkOutDate) {
        // Check if the room is available for the specified dates
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                if (!(checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate()))) {
                    // Room is not available for the specified dates
                    return false;
                }
            }
        }
        return true;
    }

    public Reservation makeReservation(User user, Room room, Date checkInDate, Date checkOutDate) {
        if (room.getIsAvailable()) {
            Reservation reservation = new Reservation(reservationIdCounter++, user, room, checkInDate, checkOutDate);
            reservations.add(reservation);
            room.setAvailable(false);
            return reservation;
        } else {
            System.out.println("Room " + room.getRoomNumber() + " is not available for the specified dates.");
            return null;
        }
    }

    public void displayReservationDetails(Reservation reservation) {
        System.out.println("Reservation Details:");
        System.out.println("Reservation ID: " + reservation.getReservationId());
        System.out.println("User: " + reservation.getUser().getUsername());
        System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
        System.out.println("Room Type: " + reservation.getRoom().getRoomType());
        System.out.println("Check-In Date: " + reservation.getCheckInDate());
        System.out.println("Check-Out Date: " + reservation.getCheckOutDate());
        System.out.println("Total Price: " + calculateTotalPrice(reservation));
    }

    private double calculateTotalPrice(Reservation reservation) {
        long diffInDays = (reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime()) / (24 * 60 * 60 * 1000);
        return diffInDays * reservation.getRoom().getPrice();
    }

    public void cancelReservation(Reservation reservation) {
        // Remove reservation from the list
        reservations.remove(reservation);
        // Set the room as available again
        reservation.getRoom().setAvailable(true);
        System.out.println("Reservation canceled successfully.");
    }

    public List<Reservation> getAllReservations() {
        // Return a list of all reservations
        return reservations;
    }
}

public class HotelReservationSystem {

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    makeReservation(hotel, scanner);
                    break;
                case 2:
                    cancelReservation(hotel, scanner);
                    break;
                case 3:
                    displayAllReservations(hotel);
                    break;
                case 4:
                    System.out.println("Exiting Hotel Reservation System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Hotel Reservation System Menu:");
        System.out.println("1. Make Reservation");
        System.out.println("2. Cancel Reservation");
        System.out.println("3. Display All Reservations");
        System.out.println("4. Exit");
    }

    private static void makeReservation(Hotel hotel, Scanner scanner) {
        System.out.println("Enter user details:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);

        System.out.println("Enter reservation details:");
        System.out.print("Check-In Date (yyyy-MM-dd): ");
        Date checkInDate = parseDate(scanner.nextLine());

        System.out.print("Check-Out Date (yyyy-MM-dd): ");
        Date checkOutDate = parseDate(scanner.nextLine());

        System.out.print("Enter room type: ");
        String roomType = scanner.nextLine();

        List<Room> availableRooms = hotel.getAvailableRooms(checkInDate, checkOutDate, roomType);
        if (!availableRooms.isEmpty()) {
            System.out.println("Available Rooms:");
            for (Room room : availableRooms) {
                System.out.println(room.getRoomNumber() + ". " + room.getRoomType() + " - $" + room.getPrice());
            }

            System.out.print("Select a room (Enter room number): ");
            int roomNumber = scanner.nextInt();

            Room selectedRoom = null;
            for (Room room : availableRooms) {
                if (room.getRoomNumber() == roomNumber) {
                    selectedRoom = room;
                    break;
                }
            }

            if (selectedRoom != null) {
                Reservation reservation = hotel.makeReservation(user, selectedRoom, checkInDate, checkOutDate);
                hotel.displayReservationDetails(reservation);
            } else {
                System.out.println("Invalid room selection.");
            }
        } else {
            System.out.println("No available rooms found for the specified dates and room type.");
        }
    }

    private static void cancelReservation(Hotel hotel, Scanner scanner) {
        System.out.print("Enter Reservation ID to cancel: ");
        int reservationId = scanner.nextInt();

        Reservation reservationToCancel = null;
        for (Reservation reservation : hotel.getAllReservations()) {
            if (reservation.getReservationId() == reservationId) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel != null) {
            hotel.cancelReservation(reservationToCancel);
            System.out.println("Reservation canceled successfully.");
        } else {
            System.out.println("Invalid Reservation ID.");
        }
    }

    private static void displayAllReservations(Hotel hotel) {
        List<Reservation> reservations = hotel.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("All Reservations:");
            for (Reservation reservation : reservations) {
                hotel.displayReservationDetails(reservation);
                System.out.println("------------------------");
            }
        }
    }

    private static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }
}