package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private String systemDate = "";

  public VenueHireSystem() {
  }

  public void printVenues() {
    if (this.venues.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
    } else if (this.venues.size() == 1) {
      Venue venue = this.venues.get(0);
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(venue.getName(), venue.getCode(), String.valueOf(venue.getCapacity()),
          String.valueOf(venue.getHireFee()));
    } else if (this.venues.size() < 10) {
      String numbers[] = {
          "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
      };
      MessageCli.NUMBER_VENUES.printMessage("are", numbers[this.venues.size()], "s");
      for (Venue i : this.venues) {
        MessageCli.VENUE_ENTRY.printMessage(i.getName(), i.getCode(), String.valueOf(i.getCapacity()),
            String.valueOf(i.getHireFee()));
      }
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(this.venues.size()), "s");
      for (Venue i : this.venues) {
        MessageCli.VENUE_ENTRY.printMessage(i.getName(), i.getCode(), String.valueOf(i.getCapacity()),
            String.valueOf(i.getHireFee()));
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
      if (Integer.parseInt(capacityInput) <= 0) { // Parses capacity to int and checks if it's non-positive
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (NumberFormatException e) { // Catches error if capacity is not an integer
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    try {
      if (Integer.parseInt(hireFeeInput) <= 0) { // Parses hire fee to int and checks if it's non-positive
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

    this.venues.add(new Venue(venueName, venueCode, capacityInput, hireFeeInput)); // Executes if all input format
                                                                                   // checks pass
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    this.systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);

    for (Venue i : this.venues) {
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

    Venue venue = null;
    for (Venue i : this.venues) {
      if (i.getCode().equals(options[0])) {
        venue = i;
        for (Booking j : i.getBookings()) {
          if (j.getDate().equals(options[1])) {
            MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venue.getName(), options[1]);
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

    if (systemDateYMD.compareTo(bookingDateYMD) > 0) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], this.systemDate);
      return;
    }

    String quarterCapacity = String.valueOf(venue.getCapacity() / 4);
    String capacity = String.valueOf(venue.getCapacity());
    if (Integer.parseInt(options[3]) < venue.getCapacity() / 4) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], quarterCapacity, capacity);
      options[3] = quarterCapacity;
    } else if (Integer.parseInt(options[3]) > venue.getCapacity()) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(options[3], capacity, capacity);
      options[3] = capacity;
    }

    String bookingReference = venue.addBooking(options[1], options[2], options[3], systemDate);
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(bookingReference, venue.getName(), options[1], options[3]);
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
