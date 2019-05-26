package inherideg;

import java.util.List;

public class Deg extends SqlPositionObject {

    private String dogId;
    private String name;

    public Deg(String dogId, String name, int x, int y) {
        super(x, y);
        this.dogId = dogId;
        this.name = name;
    }

    public String getDogId() {
        return dogId;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<ColumnValue> updateValues() {
        return List.of(
                new ColumnValue(
                        "name",
                        () -> name,
                        o -> name = (String)o),
                new ColumnValue(
                        "x",
                        this::getX,
                        o -> setX((int)o)),
                new ColumnValue(
                        "y",
                        this::getY,
                        o -> setY((int)o)));
    }

    @Override
    public List<ColumnValue> updateKeys() {
        return List.of(new ColumnValue(
                "dogId",
                () -> dogId,
                o -> dogId = (String)o));
    }

    @Override
    public String tableName() {
        return "deg";
    }

    public void woof() {
        System.out.println(name + " (" + dogId + "): Woof!");
    }

    @Override
    public String toString() {
        return name + " (" + dogId + ") (" + getX() + ", " + getY() + ")";
    }

}
