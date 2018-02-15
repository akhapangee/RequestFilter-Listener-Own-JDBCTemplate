/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.core.dao.impl;

import com.akhilesh.core.builder.EnquiryBuilder;
import com.akhilesh.core.dao.EnquiryDAO;
import com.akhilesh.core.entity.Course;
import com.akhilesh.core.entity.Enquiry;
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
public class EnquiryDAOImpl implements EnquiryDAO {

    JdbcTemplate<Enquiry> template = new JdbcTemplate<>();

    @Override
    public List<Enquiry> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from enquiries";
        return template.query(sql, new RowMapper<Enquiry>() {
            @Override
            public Enquiry mapRow(ResultSet rs) throws SQLException {
                Enquiry enquiry = EnquiryBuilder.create()
                        .setId(rs.getInt("id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setEmail(rs.getString("email"))
                        .setContactNo(rs.getString("contact_no"))
                        .setCourse(new Course(rs.getInt("course_id")))
                        .setStatus(new EnquiryStatus(rs.getInt("status")))
                        .build();
                return enquiry;
            }
        });
    }

    @Override
    public Enquiry getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from enquiries where id=?";
        return template.queryForObject(sql, new Object[]{id}, new RowMapper<Enquiry>() {
            @Override
            public Enquiry mapRow(ResultSet rs) throws SQLException {
                return EnquiryBuilder.create()
                        .setId(rs.getInt("id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setEmail(rs.getString("email"))
                        .setContactNo(rs.getString("contact_no"))
                        .setCourse(new Course(rs.getInt("course_id")))
                        .setStatus(new EnquiryStatus(rs.getInt("status")))
                        .build();
            }
        });
    }

    @Override
    public int insert(Enquiry t) throws ClassNotFoundException, SQLException {
        String sql = "insert into enquiries(first_name, last_name, email, contact_no, course_id, status )"
                + "values (?,?,?,?,?,?)";
        return template.update(sql, new Object[]{
            t.getFirstName(), t.getLastName(), t.getEmail(), t.getContactNo(), t.getCourse().getId(),
            t.getStatus().getId()
        });
    }

    @Override
    public int update(Enquiry t) throws ClassNotFoundException, SQLException {
       return 0;
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException {
        String sql = "delete from enquiries where id = ?";
        return template.update(sql, new Object[]{
            id
        });
    }

}
