package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import java.util.ArrayList;

public class VenueHireSystem {

  private ArrayList<String[]> venues = new ArrayList<String[]>();

  public VenueHireSystem() {}

  public void printVenues() {
    if (this.venues.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
    } else if (this.venues.size() == 1) {
      String venue[] = this.venues.get(0);
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(venue[0], venue[1], venue[2], venue[3]);
    } else if (this.venues.size() < 10) {
      String numbers[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
      MessageCli.NUMBER_VENUES.printMessage("are", numbers[this.venues.size()], "s");
      for (String[] i : this.venues) {
        MessageCli.VENUE_ENTRY.printMessage(i[0], i[1], i[2], i[3]);
      }
    }
  }

  public void createVenue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    String venue[] = {venueName.trim(), venueCode.trim(), capacityInput, hireFeeInput};

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

    for (String[] i : this.venues) { // Checks if venue code is already used by existing venues
      if (i[1].equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, i[0]);
        return;
      }
    }

    this.venues.add(venue); // Executes if all input format checks pass
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
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
