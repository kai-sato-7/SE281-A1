package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

    private String name;
    private String code;
    private int capacity;
    private int hireFee;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();

    public Venue(String name, String code, String capacity, String hireFee) {
        this.name = name;
        this.code = code;
        this.capacity = Integer.parseInt(capacity);
        this.hireFee = Integer.parseInt(hireFee);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHireFee() {
        return this.hireFee;
    }

    public void setHireFee(int hireFee) {
        this.hireFee = hireFee;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

    public String addBooking(String date, String email, String attendees) {
        Booking booking = new Booking(date, email, attendees);
        this.bookings.add(booking);
        return booking.getBookingReference();
    }
}
