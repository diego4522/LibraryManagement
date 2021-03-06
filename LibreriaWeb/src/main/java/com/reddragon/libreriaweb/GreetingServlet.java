/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reddragon.libreriaweb;

import com.reddragon.libreriaweb.model.CheckOut;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
@WebServlet(name = "GreetingServlet", urlPatterns = {"/GreetingServletPath"})
public class GreetingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GreetingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GreetingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
         PrintWriter out=response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String username=request.getParameter("userName");
        String password=request.getParameter("password");
        //out.println("Hello from POST Method " + username );
        
    if(username.equals("admin") && password.equals("admin")) {
         RequestDispatcher view = request.getRequestDispatcher("adminsection.jsp");
                           view.forward(request, response);
                           return;
         		        	
    }
    else{
    List<String> errorMsgs=new ArrayList<String>();
    Connection con = null;  
            //PreparedStatement stmt = null;
            ResultSet rs = null;
            Statement stmt = null;
            //send the ErrorPage view.
	request.setAttribute("errorMsgs", errorMsgs);
            try {
            String host="jdbc:derby://localhost:1527/Libreria";
            String name="root";
            String pass="root";
             con =DriverManager.getConnection(host, name, pass);
             stmt = con.createStatement();
             String sql="select * from USERS where username = '"+username+"' and password = '"+password+"'";
             rs = stmt.executeQuery(sql);
             String id = null;

             if(rs.next()){
                   id= rs.getString(1);
                   

             }else{
                     errorMsgs.add("Invalid username or password");
                     RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                     view.forward(request, response);
                     return;
             }
           //Send the ErrorPage view if there were errors
             if(!errorMsgs.isEmpty())  {
                           RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                           view.forward(request, response);
                           return;
                   }      
            //stmt = con.prepareStatement("select * from Checkout where username = ? order by return_date");
            //stmt.setString(1, username);
            //rs = stmt.executeQuery();
            //stmt = con.createStatement();
            sql="select * from Checkout where username =  '"+username+"' order by return_date";
             rs = stmt.executeQuery(sql);
            List<CheckOut> checkedOutItems = new ArrayList<CheckOut>();
            while(rs.next()){
                    CheckOut item = new CheckOut();
                    item.setTransactionId(rs.getInt(1));
                    item.setBookId(rs.getInt(2));	  
                    item.setUserName(rs.getString(3));
                    item.setReturnDate(rs.getDate(4));
                    checkedOutItems.add(item);
            }
            request.setAttribute("checkedOutItems", checkedOutItems);
                  
           //Send the success view
                   RequestDispatcher view = request.getRequestDispatcher("successGreeting.jsp");
                   view.forward(request, response);
                   return;
            }

           catch (SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
             } finally {
             try {
             if(rs != null) {
             rs.close();
             rs = null;
             }
             if(stmt != null) {
             stmt.close();
             stmt = null;
             }
             if(con != null) {
             con.close();
             con = null;
             }
             } catch (SQLException e) {}
             }
       } 
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}