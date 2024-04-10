package nz.ac.auckland.se281;

public class FloralService extends Service {

  public FloralService(String name, Integer cost) {
    super(name, cost);
  }

  @Override
  public String getDescription() {
    return String.format("Floral (%s)", this.getName());
  }
}
