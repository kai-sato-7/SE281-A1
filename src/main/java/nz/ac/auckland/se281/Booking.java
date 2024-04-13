package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String bookingReference;
  private String bookingDate;
  private String partyDate;
  private String email;
  private Integer attendees;
  private ArrayList<Service> services = new ArrayList<Service>();

  public Booking(String bookingDate, String partyDate, String email, String attendees) {
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
    this.bookingDate = bookingDate;
    this.partyDate = partyDate;
    this.email = email;
    this.attendees = Integer.parseInt(attendees);
  }

  public String getBookingReference() {
    return this.bookingReference;
  }

  public String getBookingDate() {
    return this.bookingDate;
  }

  public void setBookingDate(String bookingDate) {
    this.bookingDate = bookingDate;
  }

  public String getPartyDate() {
    return this.partyDate;
  }

  public void setPartyDate(String partyDate) {
    this.partyDate = partyDate;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getAttendees() {
    return this.attendees;
  }

  public void setAttendees(Integer attendees) {
    this.attendees = attendees;
  }

  public void addService(Service service) {
    this.services.add(service);
  }

  public ArrayList<Service> getServices() {
    return this.services;
  }

  public Integer getTotalServiceCost() { // Returns the total cost of all services in the booking, does not include
                                         // venue hire fee
    Integer totalCost = 0;
    for (Service service : this.services) {
      totalCost += service.getCost();
    }
    return totalCost;
  }
}
