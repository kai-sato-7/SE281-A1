package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String bookingReference;
  private String date;
  private String email;
  private Integer attendees;
  private ArrayList<Service> services = new ArrayList<Service>();

  public Booking(String date, String email, String attendees) {
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
    this.date = date;
    this.email = email;
    this.attendees = Integer.parseInt(attendees);
  }

  public String getBookingReference() {
    return this.bookingReference;
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

  public Integer getAttendees() {
    return this.attendees;
  }

  public void setAttendees(Integer attendees) {
    this.attendees = attendees;
  }

  public void addService(Service service) {
    this.services.add(service);
  }

  public ArrayList<Service> getServices(Service service) {
    return this.services;
  }
}
