/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.web.listener;

import com.akhilesh.core.entity.Enquiry;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Akhilesh
 */
@WebListener
public class EnquiryNotifyListerner implements ServletRequestAttributeListener{

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        if(srae.getName().equalsIgnoreCase("notify_enrollment")){
            HttpServletRequest request = (HttpServletRequest) srae.getServletRequest();
            Enquiry enquiry = (Enquiry) request.getAttribute("enquiry");
            System.out.println(enquiry.getEmail());
            System.out.println("get ready for email");
        }
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
