package nz.ac.auckland.se281;

public class Venue {

    private String name;
    private String code;
    private int capacity;
    private int hireFee;

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
}
