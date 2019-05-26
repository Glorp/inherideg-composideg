package inherideg;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ColumnValue {

    private final String column;
    private final Supplier<Object> getter;
    private final Consumer<Object> setter;

    public ColumnValue(String column, Supplier<Object> getter, Consumer<Object> setter) {
        this.column = column;
        this.getter = getter;
        this.setter = setter;
    }

    public String getColumn() {
        return column;
    }

    public Supplier<Object> getGetter() {
        return getter;
    }

    public Consumer<Object> getSetter() {
        return setter;
    }

    @Override
    public String toString() {
        return "ColumnValue(" + column + ")";
    }
}
