package inherideg;

import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SqlObject {

    public abstract String tableName();
    public abstract List<ColumnValue> updateValues();
    public abstract List<ColumnValue> updateKeys();

    private String updateSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(tableName());
        sb.append(" SET ");
        sb.append(columnsValuesString(updateValues()));
        sb.append(" WHERE ");
        sb.append(columnsValuesString(updateKeys()));
        return sb.toString();
    }

    private String selectSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(columnsString(updateValues()));
        sb.append(" FROM ");
        sb.append(tableName());
        sb.append(" WHERE ");
        sb.append(columnsValuesString(updateKeys()));
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

    public void retrieve() throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(selectSql());

        int i = 1;
        for (ColumnValue cv : updateKeys()) {
            setParameter(stmt, i, cv.getGetter().get());
            i = i + 1;
        }

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            throw new RuntimeException("not found :(");
        }

        i = 1;
        for (ColumnValue cv : updateValues()) {
            cv.getSetter().accept(rs.getObject(i));
            i = i + 1;
        }

    }

    public void save() throws SQLException {
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(updateSql());
        int i = 1;

        for (ColumnValue cv : updateValues()) {
            setParameter(stmt, i, cv.getGetter().get());
            i = i + 1;
        }

        for (ColumnValue cv : updateKeys()) {
            setParameter(stmt, i, cv.getGetter().get());
            i = i + 1;
        }
        if (stmt.executeUpdate() != 1) {
            throw new RuntimeException("oh no");
        }
    }

    private String columnsString(List<ColumnValue> list) {
        return list.stream()
                .map(ColumnValue::getColumn)
                .collect(Collectors.joining(", "));
    }

    private String columnsValuesString(List<ColumnValue> list) {
        return list.stream()
                .map(x -> x.getColumn() + " = ?")
                .collect(Collectors.joining(", "));
    }

}
