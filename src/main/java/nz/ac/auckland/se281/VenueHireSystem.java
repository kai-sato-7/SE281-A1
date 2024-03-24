package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import java.util.ArrayList;

public class VenueHireSystem {

  ArrayList<String[]> venues = new ArrayList<String[]>();

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method
  }

  public void createVenue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    String venue[] = {venueName, venueCode, capacityInput, hireFeeInput};
    if (venueName.trim() == "") {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else {
      this.venues.add(venue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }
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
