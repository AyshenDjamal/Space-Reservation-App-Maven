package org.example;

public class AdminConsole {


    public static void addSpaceWithUserInput() {
        while (true) {
            try{
            System.out.print("Enter Space ID: ");
            int id = Main.input.nextInt();

            if (WorkSpaceService.coworkingSpaces.containsKey(id)) {
                throw new ApplicationException ("The ID already exists, please enter a new ID");
            }

            System.out.print("Enter Space Type (open/private): ");
            String spaceType = Main.input.next();
            System.out.print("Enter Price: ");
            double price = Main.input.nextDouble();
            System.out.print("Is available? (true/false): ");
            boolean isAvailable = Main.input.nextBoolean();

            if(WorkSpaceService.addSpace(id, spaceType, price, isAvailable)) {
                System.out.println("----------------------------------");
                System.out.println("New coworking space added successfully!\n");

                System.out.println("Go back to Admin Menu or add another space? (back/add)");
                System.out.print("Enter your choice: ");
                String answer = Main.input.next();

                if (answer.equalsIgnoreCase("Back")) {
                    Main.adminMenu();
                    return;
                }
            }
        }catch (ApplicationException e){
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                Main.input.nextLine();
            }
        }
    }


        public static void removeSpaceWithUserInput () {
            while (true) {
                System.out.print("Enter the Space ID to be removed: ");
                int id = Main.input.nextInt();
                if (!WorkSpaceService.coworkingSpaces.containsKey(id)) {
                    System.out.println("Enter the correct space ID\n");
                    continue;
                }

                if (WorkSpaceService.removeSpace(id)) {
                    System.out.println("----------------------------");
                    System.out.println("Space removed successfully!");

                    System.out.println("\nSelect '1' to go back to the Admin Menu or '2' to remove a space. (1/2)");
                    System.out.print("Enter your choice: ");
                    int num = Main.input.nextInt();

                    if (num == 1) {
                        Main.adminMenu();
                        break;
                    }
                } else {
                    System.out.println("-----------------------------------");
                    System.out.println("This coworking space is booked and cannot be removed.\n");
                }
            }
        }


        public static void returnToAdminMenu () {
            WorkSpaceService.viewAllBookings();
            System.out.println("\nSelect '1' to go back to the Admin Menu");
            System.out.print("Enter your choice: ");
            int opt = Main.input.nextInt();
            if (opt == 1) {
                Main.adminMenu();
            }
        }
    }






