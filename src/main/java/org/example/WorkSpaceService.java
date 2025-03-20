package org.example;

import java.io.*;
import java.util.HashMap;

public class WorkSpaceService {
    public static HashMap<Integer, CoworkingSpace> coworkingSpaces = new HashMap<>();

    public WorkSpaceService() {
        coworkingSpaces.put(1, new CoworkingSpace(1, "Open", 12.4, true));
        coworkingSpaces.put(2, new CoworkingSpace(2, "Open", 12.4, true));
        coworkingSpaces.put(3, new CoworkingSpace(3, "Open", 12.4, true));
    }

    public static boolean addSpace(int id, String spaceType, double price, boolean isAvailable) {
        if (!coworkingSpaces.containsKey(id)) {
            coworkingSpaces.put(id, new CoworkingSpace(id, spaceType, price, isAvailable));
            return true;
        }
        return false;
    }


    public static boolean removeSpace(int id) {
        if (coworkingSpaces.containsKey(id)) {
            CoworkingSpace space = coworkingSpaces.get(id);

            if (space.getIsAvailable()) {
                coworkingSpaces.remove(id);
                return true;
            }
        }
        return false;
    }


        public static void viewAllBookings () {
            if (ReservationService.reservations.isEmpty()) {
                System.out.println("------------------------------");
                System.out.println("No reservations were found.\n");
            } else {
                System.out.println("-------------List Of Reservations-----------");
                ReservationService.reservations.values().forEach(System.out::println);
            }
        }


        public void saveSpaces () {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("spaces.ser"))) {
                out.writeObject(coworkingSpaces);
                out.writeObject(ReservationService.reservations);
                System.out.println("Data saved successfully!");
            } catch (IOException e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        }


        public void loadSpaces () {
            File file = new File("spaces.ser");
            if (file.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    coworkingSpaces = (HashMap<Integer, CoworkingSpace>) in.readObject();
                    ReservationService.reservations = (HashMap<Integer, Reservation>) in.readObject();
                    System.out.println("Spaces restored successfully");
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error loading spaces: " + e.getMessage());
                }
            } else {
                System.out.println("Spaces haven't been saved. Restore spaces. ");
            }

        }
    }




