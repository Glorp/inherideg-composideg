package composideg;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class ColumnValue<T> {

    private final String column;
    private final Function<T, Object> getter;
    private final BiConsumer<T, Object> setter;

    public ColumnValue(String column, Function<T, Object> getter, BiConsumer<T, Object> setter) {
        this.column = column;
        this.getter = getter;
        this.setter = setter;
    }

    public String getColumn() {
        return column;
    }

    public Function<T, Object> getGetter() {
        return getter;
    }

    public BiConsumer<T, Object> getSetter() {
        return setter;
    }

    @Override
    public String toString() {
        return "ColumnValue(" + column + ")";
    }
}
