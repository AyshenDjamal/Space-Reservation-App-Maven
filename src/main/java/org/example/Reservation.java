package org.example;

public class Reservation  {
    public int bookingID;
    public String customerName;
    public String date;
    public String startTime;
    public String endTime;
    public int spaceID;

    public Reservation(int bookingID, String customerName, String date, String startTime, String endTime, int spaceID){
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spaceID = spaceID;
    }

    @Override
    public String toString(){
        return
                "Booking ID: " + bookingID +
                        " | Name: " + customerName +
                        " | Date: " + date +
                        " | Start Time: " + startTime +
                        " | End Time: " + endTime +
                        " | Space ID: " + spaceID;

    }
}


