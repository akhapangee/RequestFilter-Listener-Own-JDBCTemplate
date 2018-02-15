package com.akhilesh.web.controller;

import com.akhilesh.core.builder.EnquiryBuilder;
import com.akhilesh.core.dao.CourseDAO;
import com.akhilesh.core.dao.EnquiryDAO;
import com.akhilesh.core.dao.impl.CourseDAOImpl;
import com.akhilesh.core.dao.impl.EnquiryDAOImpl;
import com.akhilesh.core.entity.Course;
import com.akhilesh.core.entity.Enquiry;
import com.akhilesh.core.entity.EnquiryStatus;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akhilesh
 */
@WebServlet(name = "enroll", urlPatterns = "/enroll")
public class EnrollController extends Controller {

    private CourseDAO courseDAO = new CourseDAOImpl();
    private EnquiryDAO enquiryDAO = new EnquiryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("courses", courseDAO.getAll());
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        view("enroll/index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Enquiry enquiry = EnquiryBuilder.create()
                    .setFirstName(req.getParameter("first_name"))
                    .setLastName(req.getParameter("last_name"))
                    .setEmail(req.getParameter("email"))
                    .setContactNo(req.getParameter("contact_no"))
                    .setCourse(new Course(Integer.parseInt(req.getParameter("course_id"))))
                    .setStatus(new EnquiryStatus())
                    .setStatus(new EnquiryStatus(16))
                    .build();
            enquiryDAO.insert(enquiry);
            req.setAttribute("enquiry", enquiry);
            req.setAttribute("notify_enrollment", "1");
            redirect(req.getContextPath()+"/enroll?true", resp);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
