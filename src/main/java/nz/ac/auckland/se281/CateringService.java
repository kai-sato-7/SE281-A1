package nz.ac.auckland.se281;

public class CateringService extends Service {

  private Integer costPerPerson;

  public CateringService(String name, Integer costPerPerson, Integer attendees) {
    super(name, costPerPerson * attendees);
    this.costPerPerson = costPerPerson;
  }

  public Integer getCostPerPerson() {
    return costPerPerson;
  }

  @Override
  public String getDescription() {
    return String.format("Catering (%s)", this.getName());
  }
}
