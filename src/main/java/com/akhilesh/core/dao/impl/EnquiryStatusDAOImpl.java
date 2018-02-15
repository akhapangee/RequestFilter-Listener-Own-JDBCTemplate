/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.core.dao.impl;

import com.akhilesh.core.dao.EnquiryStatusDAO;
import com.akhilesh.core.entity.EnquiryStatus;
import com.akhilesh.core.template.JdbcTemplate;
import com.akhilesh.core.template.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Akhilesh
 */
public class EnquiryStatusDAOImpl implements EnquiryStatusDAO {

    JdbcTemplate<EnquiryStatus> template = new JdbcTemplate<>();

    @Override
    public List<EnquiryStatus> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from enquiry_status";
        return template.query(sql, new RowMapper<EnquiryStatus>() {
            @Override
            public EnquiryStatus mapRow(ResultSet rs) throws SQLException {
                EnquiryStatus status = new EnquiryStatus();
                status.setId(rs.getInt("id"));
                status.setName(rs.getString("name"));
                status.setColor(rs.getString("color"));
                return status;
            }
        });
    }

    @Override
    public EnquiryStatus getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from enquiry_status where id=?";
        return template.queryForObject(sql, new Object[]{id}, new RowMapper<EnquiryStatus>() {
            @Override
            public EnquiryStatus mapRow(ResultSet rs) throws SQLException {
                EnquiryStatus status = new EnquiryStatus();
                status.setId(rs.getInt("id"));
                status.setName(rs.getString("name"));
                status.setColor(rs.getString("color"));
 
                return status;
            }
        });
    }

    @Override
    public int insert(EnquiryStatus t) throws ClassNotFoundException, SQLException {
        String sql = "insert into enquiry_status(name,color)"
                + "where id = ?";
        return template.update(sql, new Object[]{
            t.getName(),t.getColor()
        });
    }

    @Override
    public int update(EnquiryStatus t) throws ClassNotFoundException, SQLException {
        String sql = "update enquiry_status set name=?, color=? "
                + "values(?,?)";
        return template.update(sql, new Object[]{
            t.getName(),t.getColor()
        });
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException {
        String sql = "delete from enquiry_status where id = ?";
        return template.update(sql, new Object[]{
           id
        });
    }

}
