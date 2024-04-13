package nz.ac.auckland.se281;

public abstract class Service { // Defines an abstract class for other services to extend

  private String name;
  private Integer cost;

  public Service(String name, Integer cost) {
    this.name = name;
    this.cost = cost;
  }

  public String getName() {
    return name;
  }

  public Integer getCost() {
    return cost;
  }

  public abstract String getDescription();
}
