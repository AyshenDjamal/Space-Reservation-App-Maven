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

            if (ReservationService.reservations.containsKey(resID)) {
                System.out.println("Sorry, this space ID has already been taken. \nPlease select a different space.\n");
                continue;
            }

                System.out.print("Enter your name: ");
                String name = Main.input.nextLine();
                System.out.print("Enter reservation date: ");
                String date = Main.input.nextLine();
                System.out.print("Enter start time: ");
                String start = Main.input.nextLine();
                System.out.print("Enter end time: ");
                String end = Main.input.nextLine();

                if (ReservationService.bookSpace(resID, name, date, start, end)) {
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
        ReservationService.myBookings();
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = Main.input.nextInt();
        Main.customerMenu();
    }

    public static void handleCancellation() {
        System.out.println("-----------Cancel Your Booking-----------");
        if (ReservationService.reservations.isEmpty()) {
            System.out.println("You don't have a booking. \n");
            System.out.println("\nSelect '1' to go back to the Customer Menu");
            System.out.print("Enter your choice: ");
            int opt = Main.input.nextInt();
            Main.customerMenu();
        }

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
