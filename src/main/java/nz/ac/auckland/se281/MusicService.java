package nz.ac.auckland.se281;

public class MusicService extends Service {

  public MusicService() {
    super("Music", 500);
  }

  @Override
  public String getDescription() {
    return "Music";
  }
}
