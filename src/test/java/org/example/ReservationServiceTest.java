package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    @BeforeEach
    void setUp() {
        WorkSpaceService.coworkingSpaces.clear();
        ReservationService.reservations.clear();
    }

    @Test
    void testBookSpace_Success() {
        WorkSpaceService.addSpace(1, "private",23, true);
        boolean result = ReservationService.bookSpace(1, "Ayshen", "23 march 2025", "2 p.m", "3 p.m");
        assertTrue(result, "Should be booked successfully");
    }

    @Test
    void testBookSpace_Fails_WhenAlreadyBooked() {
        WorkSpaceService.addSpace(2, "private", 30, true);
        ReservationService.bookSpace(2, "Ayshen", "03-10-2025", "14:00", "16:00");
        boolean duplicateResult = ReservationService.bookSpace(2, "Aysel", "04-10-2025", "15:00", "17:00");
        assertFalse(duplicateResult, "Should not allow double booking.");
    }

    @Test
    void testBookSpace_Fails_WhenSpaceDoesNotExist() {
        boolean result = ReservationService.bookSpace(7, "Ayshen Djamal", "03-19-2025", "10:00", "12:00");
        assertFalse(result, "Should not allow booking for a non-existent space");
    }

    @Test
    void testCancelBooking_Success(){
        WorkSpaceService.addSpace(1, "open", 20.0, true);
        ReservationService.bookSpace(1, "Ayshen", "22 march 2025", "2 p.m", "3 p.m");
        assertTrue(ReservationService.cancelBooking(1), "Reservation should be canceled.");
    }

    @Test
    void testCancelBooking_Fails_WhenNoBookingExists(){
        assertFalse(ReservationService.reservations.containsKey(3), "Reservation ID 3 should not exist before cancellation.");
        boolean result = ReservationService.cancelBooking(3);
        assertFalse(result, "Should not cancel a reservation that does not exist.");
    }
}