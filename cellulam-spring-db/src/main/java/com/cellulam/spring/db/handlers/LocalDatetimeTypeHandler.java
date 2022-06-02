package com.cellulam.spring.db.handlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.LocalDateTime;

@MappedTypes(LocalDateTime.class)
@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class LocalDatetimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, Timestamp.valueOf(parameter));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp sqlTimestamp = rs.getTimestamp(columnName);
        if (sqlTimestamp != null) {
            return sqlTimestamp.toLocalDateTime();
        }
        return null;
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnIndex);
        if (timestamp != null) {
            return timestamp.toLocalDateTime();
        }
        return null;
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp timestamp = cs.getTimestamp(columnIndex);
        if (timestamp != null) {
            return timestamp.toLocalDateTime();
        }
        return null;
    }
}
