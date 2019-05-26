package composideg;

import java.util.List;

public interface Table<T> {
    String tableName();
    List<ColumnValue<T>> updateValues();
    List<ColumnValue<T>> updateKeys();
}
