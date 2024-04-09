package nz.ac.auckland.se281;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Venue {

  private String name;
  private String code;
  private int capacity;
  private int hireFee;
  private String nextAvailableDate = "";
  private ArrayList<String> bookingDates = new ArrayList<String>();
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

  public String addBooking(String date, String email, String attendees, String systemDate) {
    Booking booking = new Booking(date, email, attendees);
    this.bookings.add(booking);
    this.bookingDates.add(date);
    this.updateNextAvailableDate(systemDate);
    return booking.getBookingReference();
  }

  public String getNextAvailableDate() {
    return this.nextAvailableDate;
  }

  public void updateNextAvailableDate(String systemDate) {
    this.nextAvailableDate = systemDate;
    this.bookingDates
        .sort((String a, String b) -> (a.split("/")[0] + a.split("/")[1] + a.split("/")[2])
            .compareTo(b.split("/")[0] + b.split("/")[1] + b.split("/")[2]));

    for (String i : this.bookingDates) {
      if (i.compareTo(this.nextAvailableDate) == 0) {
        this.nextAvailableDate = this.getNextDay(this.nextAvailableDate);
      } else if (i.compareTo(this.nextAvailableDate) > 0) {
        break;
      }
    }
    System.out.println(this.nextAvailableDate);
  }

  public String getNextDay(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar c = Calendar.getInstance();
    try {
      c.setTime(sdf.parse(date));
    } catch (Exception e) {
      e.printStackTrace();
    }
    c.add(Calendar.DATE, 1);
    return sdf.format(c.getTime());
  }
}
