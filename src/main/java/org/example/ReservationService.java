package org.example;

import java.util.List;

public class ReservationService {


    public static void viewSpaces() {
        List<CoworkingSpace> spaceList = ReservationRepositoryJPA.getAvailableSpaces();
        if (spaceList.isEmpty()) {
            System.out.println("No coworking spaces are available.");
        } else {
            System.out.println("-------------List of Coworking Spaces----------");
            spaceList.stream()
                    .filter(CoworkingSpace::getIsAvailable)
                    .forEach(System.out::println);
        }
    }



    public static boolean bookSpace(Reservation reservation) {
        if(reservation.getSpace() == null || !reservation.getSpace().getIsAvailable()){
            System.out.println("Space is either invalid or already booked! ");
            return false;
        }
        return ReservationRepositoryJPA.insertReservation(reservation);
    }



    public static CoworkingSpace findSpaceID(int spaceID){
        return SpaceRepositoryJPA.findSpaceID(spaceID);
    }


    public static void myBookings(int bookingID) {
        List<Reservation> bookingList = ReservationRepositoryJPA.getMyBooking(bookingID);
        if (bookingList.isEmpty()) {
            System.out.println("You don't have a booking. ");
        } else {
            bookingList.forEach(System.out::println);
        }
    }



    public static boolean cancelBooking(int canID) {
        return ReservationRepositoryJPA.deleteBooking(canID);
    }
}


