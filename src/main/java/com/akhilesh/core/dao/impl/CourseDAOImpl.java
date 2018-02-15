/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.core.dao.impl;

import com.akhilesh.core.dao.CourseDAO;
import com.akhilesh.core.entity.Course;
import com.akhilesh.core.template.JdbcTemplate;
import com.akhilesh.core.template.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Akhilesh
 */
public class CourseDAOImpl implements CourseDAO {

    JdbcTemplate<Course> template = new JdbcTemplate<>();

    @Override
    public List<Course> getAll() throws ClassNotFoundException, SQLException {
        String sql = "select * from courses";
        return template.query(sql, new RowMapper<Course>() {
            @Override
            public Course mapRow(ResultSet rs) throws SQLException {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setFees(rs.getInt("fees"));
                course.setCode(rs.getString("code"));
                course.setName(rs.getString("name"));
                return course;
            }
        });
    }

    @Override
    public Course getById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from courses where id=?";
        return template.queryForObject(sql, new Object[]{id}, new RowMapper<Course>() {
            @Override
            public Course mapRow(ResultSet rs) throws SQLException {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setFees(rs.getInt("fees"));
                course.setCode(rs.getString("code"));
                course.setName(rs.getString("name"));
                return course;
            }
        });
    }

    @Override
    public int insert(Course t) throws ClassNotFoundException, SQLException {
        String sql = "insert into courses(name, code, fees, status)"
                + "where id = ?";
        return template.update(sql, new Object[]{
            t.getName(),t.getCode(),t.getFees(),t.isStatus(), t.getId()
        });
    }

    @Override
    public int update(Course t) throws ClassNotFoundException, SQLException {
        String sql = "update courses set name=?, code=?, fees=?, status=?"
                + "values(?,?,?,?)";
        return template.update(sql, new Object[]{
            t.getName(),t.getCode(),t.getFees(),t.isStatus()
        });
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException {
        String sql = "delete from courses where id = ?";
        return template.update(sql, new Object[]{
           id
        });
    }

}
