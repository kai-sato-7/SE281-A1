package nz.ac.auckland.se281;

public class Booking {

    private String bookingReference;
    private String venueCode;
    private String date;
    private String email;
    private String attendees;

    public Booking(String bookingReference, String venueCode, String date, String email, String attendees) {
        this.bookingReference = bookingReference;
        this.venueCode = venueCode;
        this.date = date;
        this.email = email;
        this.attendees = attendees;
    }

    public String getBookingReference() {
        return this.bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public String getVenueCode() {
        return this.venueCode;
    }

    public void setVenueCode(String venueCode) {
        this.venueCode = venueCode;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAttendees() {
        return this.attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }
}
