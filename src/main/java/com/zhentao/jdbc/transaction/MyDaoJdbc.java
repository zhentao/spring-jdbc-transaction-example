package com.zhentao.jdbc.transaction;

import java.sql.*;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class MyDaoJdbc extends JdbcDaoSupport implements MyDao {
    private static final String INSERT_SQL = "INSERT INTO person (name) values (?)";
    @Override
    @Transactional
    public void insert(final String value) {
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_SQL);
                ps.setString(1, value);
                return ps;
            }
        });
        //throw new RuntimeException("this is runtime exception");
    }
}
