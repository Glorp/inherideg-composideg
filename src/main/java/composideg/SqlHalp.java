package composideg;

import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public final class SqlHalp<T> {

    private final Table<T> table;

    public SqlHalp(Table<T> table) {
        this.table = table;
    }

    private String updateSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(table.tableName());
        sb.append(" SET ");
        sb.append(columnsValuesString(table.updateValues()));
        sb.append(" WHERE ");
        sb.append(columnsValuesString(table.updateKeys()));
        return sb.toString();
    }

    private String selectSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(columnsString(table.updateValues()));
        sb.append(" FROM ");
        sb.append(table.tableName());
        sb.append(" WHERE ");
        sb.append(columnsValuesString(table.updateKeys()));
        return sb.toString();
    }

    public void setParameter(PreparedStatement stmt, int index, Object value) throws SQLException {
        if (value instanceof String) {
            stmt.setString(index, (String)value);
        } else if (value instanceof Integer) {
            stmt.setDouble(index, (int)value);
        } else {
            throw new RuntimeException("bad SQL value: " + value);
        }

    }

    public void retrieve(T thing) throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(selectSql());

        int i = 1;
        for (ColumnValue cv : table.updateKeys()) {
            setParameter(stmt, i, cv.getGetter().apply(thing));
            i = i + 1;
        }

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            throw new RuntimeException("not found :(");
        }

        i = 1;
        for (ColumnValue cv : table.updateValues()) {
            cv.getSetter().accept(thing, rs.getObject(i));
            i = i + 1;
        }

    }

    public void save(T thing) throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(updateSql());
        int i = 1;

        for (ColumnValue cv : table.updateValues()) {
            setParameter(stmt, i, cv.getGetter().apply(thing));
            i = i + 1;
        }

        for (ColumnValue cv : table.updateKeys()) {
            setParameter(stmt, i, cv.getGetter().apply(thing));
            i = i + 1;
        }
        if (stmt.executeUpdate() != 1) {
            throw new RuntimeException("oh no");
        }
    }

    private String columnsString(List<ColumnValue<T>> list) {
        return list.stream()
                .map(ColumnValue::getColumn)
                .collect(Collectors.joining(", "));
    }

    private String columnsValuesString(List<ColumnValue<T>> list) {
        return list.stream()
                .map(x -> x.getColumn() + " = ?")
                .collect(Collectors.joining(", "));
    }

}
