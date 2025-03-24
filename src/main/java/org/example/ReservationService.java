package org.example;

import java.util.List;

public class ReservationService {


    public static void viewSpaces() {
        List<CoworkingSpace> spaceList = ReservationRepository.getAvailableSpaces();
        if (spaceList.isEmpty()) {
            System.out.println("No coworking spaces are available.");
        } else {
            System.out.println("-------------List of Coworking Spaces----------");
            spaceList.stream()
                    .filter(CoworkingSpace::getIsAvailable)
                    .forEach(System.out::println);
        }
    }


    public static boolean bookSpace(int resID, String name, String date, String start, String end, int spaceID) {
        Reservation reservation = new Reservation(resID, name, date, start, end, spaceID);
        if (ReservationRepository.insertReservation(reservation)) {
            ReservationRepository.setSpaceBooked(spaceID);
            return true;
        }
        return false;
    }


    public static void myBookings() {
        List<Reservation> bookingList = ReservationRepository.getMyBooking();
        if (bookingList.isEmpty()) {
            System.out.println("You don't have a booking. ");
        } else {
            bookingList.forEach(System.out::println);
        }
    }



    public static boolean cancelBooking(int canID) {
        if (ReservationRepository.deleteBooking(canID)) {
            ReservationRepository.setSpaceAvailable(canID);
            return true;
        }
        return false;
    }
}


