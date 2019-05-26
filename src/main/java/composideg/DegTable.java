package composideg;

import java.util.List;

public class DegTable implements Table<DegDTO> {
    @Override
    public String tableName() {
        return "deg";
    }

    @Override
    public List<ColumnValue<DegDTO>> updateValues() {
        return List.of(
                new ColumnValue<>(
                        "name",
                        DegDTO::getName,
                        (d, o) -> d.setName((String)o)),
                new ColumnValue<>(
                        "x",
                        DegDTO::getX,
                        (d, o) -> d.setX((int)o)),
                new ColumnValue<>(
                        "y",
                        DegDTO::getY,
                        (d, o) -> d.setY((int)o)));
    }

    @Override
    public List<ColumnValue<DegDTO>> updateKeys() {
        return List.of(new ColumnValue<>(
                "dogId",
                DegDTO::getDogId,
                (d, o) -> d.setDogId((String)o)));
    }
}
