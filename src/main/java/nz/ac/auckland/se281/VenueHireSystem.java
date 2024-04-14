package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private String systemDate = "";

  public VenueHireSystem() {
  }

  public void printVenues() { // Prints all venues in the system
    if (this.venues.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
    } else if (this.venues.size() == 1) {
      Venue venue = this.venues.get(0);
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(venue.getName(), venue.getCode(),
          String.valueOf(venue.getCapacity()), String.valueOf(venue.getHireFee()),
          venue.getNextAvailableDate());
    } else if (this.venues.size() < 10) {
      String numbers[] = {
          "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
      };
      MessageCli.NUMBER_VENUES.printMessage("are", numbers[this.venues.size()], "s");
      for (Venue i : this.venues) {
        MessageCli.VENUE_ENTRY.printMessage(i.getName(), i.getCode(),
            String.valueOf(i.getCapacity()), String.valueOf(i.getHireFee()),
            i.getNextAvailableDate());
      }
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(this.venues.size()), "s");
      for (Venue i : this.venues) {
        MessageCli.VENUE_ENTRY.printMessage(i.getName(), i.getCode(),
            String.valueOf(i.getCapacity()), String.valueOf(i.getHireFee()),
            i.getNextAvailableDate());
      }
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    if (venueName.trim() == "") { // Checks if venue name is empty or spaces only
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    try {
      // Parses capacity to int and checks if it's non-positive
      if (Integer.parseInt(capacityInput) <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (NumberFormatException e) { // Catches error if capacity is not an integer
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    try {
      // Parses hire fee to int and checks if it's non-positive
      if (Integer.parseInt(hireFeeInput) <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (NumberFormatException e) { // Catches error if hire fee is not an integer
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    for (Venue i : this.venues) { // Checks if venue code is already used by existing venues
      if (i.getCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, i.getName());
        return;
      }
    }
    // Executes if all input format checks pass
    this.venues.add(new Venue(venueName, venueCode, capacityInput, hireFeeInput));
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    this.systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);

    for (Venue i : this.venues) { // Updates next available date for all venues
      i.updateNextAvailableDate(dateInput);
    }
  }

  public void printSystemDate() {
    if (this.systemDate == "") {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(this.systemDate);
    }
  }

  public void makeBooking(String[] options) {
    if (this.systemDate == "") {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }
    if (this.venues.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // Checks if venue exists and if it's not already booked on the specified date
    Venue venue = null;
    for (Venue i : this.venues) {
      if (i.getCode().equals(options[0])) {
        venue = i;
        for (Booking j : i.getBookings()) {
          if (j.getPartyDate().equals(options[1])) {
            MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
                venue.getName(), options[1]);
            return;
          }
        }
        break;
      }
    }
    if (venue == null) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
      return;
    }

    String[] systemDateArray = this.systemDate.split("/");
    String systemDateYMD = systemDateArray[2] + systemDateArray[1] + systemDateArray[0];
    String[] bookingDateArray = options[1].split("/");
    String bookingDateYMD = bookingDateArray[2] + bookingDateArray[1] + bookingDateArray[0];

    // Checks if booking date is in the past using YMD format
    if (systemDateYMD.compareTo(bookingDateYMD) > 0) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], this.systemDate);
      return;
    }

    String quarterCapacity = String.valueOf(venue.getCapacity() / 4);
    String capacity = String.valueOf(venue.getCapacity());
    // Adjust attendees if less than 25% capacity or more than capacity
    if (Integer.parseInt(options[3]) < venue.getCapacity() / 4) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], quarterCapacity, capacity);
      options[3] = quarterCapacity;
    } else if (Integer.parseInt(options[3]) > venue.getCapacity()) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], capacity, capacity);
      options[3] = capacity;
    }

    String bookingReference = venue.addBooking(options[1], options[2], options[3], systemDate);
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingReference, venue.getName(), options[1], options[3]);
  }

  public void printBookings(String venueCode) { // Prints all bookings for a venue
    for (Venue i : this.venues) {
      if (i.getCode().equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(i.getName());
        if (i.getBookings().size() == 0) {
          MessageCli.PRINT_BOOKINGS_NONE.printMessage(i.getName());
        } else {
          for (Booking j : i.getBookings()) {
            MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(j.getBookingReference(), j.getPartyDate());
          }
        }
        return;
      }
    }
    MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    for (Venue i : this.venues) { // Adds catering service to booking if booking exists
      for (Booking j : i.getBookings()) {
        if (j.getBookingReference().equals(bookingReference)) {
          CateringService cateringService = new CateringService(
              cateringType.getName(), cateringType.getCostPerPerson(), j.getAttendees());
          j.addService(cateringService);
          MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
              cateringService.getDescription(), bookingReference);
          return;
        }
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    for (Venue i : this.venues) { // Adds music service to booking if booking exists
      for (Booking j : i.getBookings()) {
        if (j.getBookingReference().equals(bookingReference)) {
          MusicService musicService = new MusicService();
          j.addService(musicService);
          MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
          return;
        }
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    for (Venue i : this.venues) { // Adds floral service to booking if booking exists
      for (Booking j : i.getBookings()) {
        if (j.getBookingReference().equals(bookingReference)) {
          FloralService floralService = new FloralService(
              floralType.getName(), floralType.getCost());
          j.addService(floralService);
          MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
              floralService.getDescription(), bookingReference);
          return;
        }
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    for (Venue i : this.venues) {
      for (Booking j : i.getBookings()) {
        if (j.getBookingReference().equals(bookingReference)) {
          MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(bookingReference,
              j.getEmail(), j.getBookingDate(),
              j.getPartyDate(), String.valueOf(j.getAttendees()), i.getName());
          MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(String.valueOf(i.getHireFee()));
          for (Service k : j.getServices()) { // Prints all services in the booking, if any
            if (k instanceof CateringService) {
              MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
                  k.getName(), String.valueOf(k.getCost()));
            } else if (k instanceof MusicService) {
              MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(String.valueOf(k.getCost()));
            } else if (k instanceof FloralService) {
              MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
                  k.getName(), String.valueOf(k.getCost()));
            }
          }
          MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(
              String.valueOf(j.getTotalServiceCost() + i.getHireFee()));
          return;
        }
      }
    }
    MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
  }
}
