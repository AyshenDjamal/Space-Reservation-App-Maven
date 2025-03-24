package org.example;

public class CustomerConsole {

    public static void returnToCustomerMenu() {
        ReservationService.viewSpaces();
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = Main.input.nextInt();
        Main.customerMenu();
    }

    public static void handleBooking() {
        System.out.println("------------Make A Reservation-------------");
        while (true) {
            System.out.print("Enter your reservation ID: ");
            int resID = Main.input.nextInt();
            Main.input.nextLine();


            System.out.print("Enter your name: ");
            String name = Main.input.nextLine();
            System.out.print("Enter reservation date: ");
            String date = Main.input.nextLine();
            System.out.print("Enter start time: ");
            String start = Main.input.nextLine();
            System.out.print("Enter end time: ");
            String end = Main.input.nextLine();
            System.out.print("Enter your space ID: ");
            int spaceID = Main.input.nextInt();

            if (ReservationService.bookSpace(resID, name, date, start, end, spaceID)) {
                System.out.println("-------------------------------------------------------");
                System.out.println("Reservation accepted! Space " + resID + "  has been booked for you.");

                System.out.println("\nSelect '1' to go back to the Customer Menu");
                System.out.print("Enter your choice: ");
                int opt = Main.input.nextInt();
                Main.customerMenu();
                break;
            }
        }

    }


    public static void returnToCustomerMenuu() {
        System.out.println("------------Display My Bookings-------------");
        ReservationService.myBookings();
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = Main.input.nextInt();
        Main.customerMenu();
    }


    public static void handleCancellation() {
        System.out.println("-----------Cancel Your Booking-----------");
        while (true) {
            System.out.print("Enter your reservation ID:");
            int canID = Main.input.nextInt();


            if (ReservationService.cancelBooking(canID)) {
                System.out.println("------------------------------");
                System.out.println("Your booking was successfully canceled!");


                System.out.println("\nSelect '1' to go back to the Customer Menu");
                System.out.print("Enter your choice: ");
                int opt = Main.input.nextInt();
                Main.customerMenu();
                break;
            } else {
                System.out.println("\n------------------------------");
                System.out.println("Enter correct booking ID. \n");
            }
        }
    }
}
