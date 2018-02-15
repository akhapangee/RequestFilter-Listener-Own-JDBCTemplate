/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.core.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akhilesh
 */
public class JdbcTemplate<T> {

    private Connection conn;
    private PreparedStatement stmt;

    public List<T> query(String sql, RowMapper<T> mapper) throws ClassNotFoundException, SQLException {
        List<T> rows = new ArrayList<>();
        connect();
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            rows.add(mapper.mapRow(rs));
        }
        conn.close();
        return rows;
    }

    public T queryForObject(String sql, Object[] args, RowMapper<T> mapper) throws ClassNotFoundException, SQLException {
        T row = null;
        connect();
        stmt = conn.prepareStatement(sql);
        setParameters(args);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            row = mapper.mapRow(rs);
        }
        conn.close();
        return row;
    }
    
    public int update(String sql, Object[] args) throws ClassNotFoundException, SQLException{
        connect();
        stmt = conn.prepareStatement(sql);
        setParameters(args);
        return stmt.executeUpdate();
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/enroll_system", "root", "");
    }

    private void setParameters(Object[] args) throws SQLException {
        int index = 1;
        
        for(Object obj : args){
            stmt.setObject(index, obj);
            index++;
        }

    }
}
