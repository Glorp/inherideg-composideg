package composideg;

public final class Deg {

    private final String dogId;
    private final String name;
    private Vector position;

     public Deg(String dogId, String name, Vector position) {
        this.dogId = dogId;
        this.name = name;
        this.position = position;
    }

    public String getDogId() {
        return dogId;
    }

    public String getName() {
        return name;
    }

    public Vector getPosition() {
        return position;
    }

    public void move(Vector vector) {
        position = position.move(vector);
    }

    public void woof() {
        System.out.println(name + " (" + dogId + "): Woof!");
    }

    @Override
    public String toString() {
        return name + " (" + dogId + ") " + position;
    }

}
