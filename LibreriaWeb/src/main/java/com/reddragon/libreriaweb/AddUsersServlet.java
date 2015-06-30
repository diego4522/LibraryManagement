/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reddragon.libreriaweb;

import com.reddragon.libreriaweb.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet(name = "AddUsersServlet", urlPatterns = {"/AddUsersServletPath"})
public class AddUsersServlet extends HttpServlet {

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
            out.println("<title>Servlet AddUsersServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUsersServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
       List<String> errorMsgs=new ArrayList<String>(); 
//JDBC variables
		 
        PrintWriter out = response.getWriter();
	Connection con = null;  
	PreparedStatement pstmt = null;
	Statement statement=null;
		
	//send the ErrorPage view.
	request.setAttribute("errorMsgs", errorMsgs);
	
	try  {
		//Retrieve form parameters
		String firstName=request.getParameter("firstname").trim();
		String surname=request.getParameter("surname").trim();
		String age=request.getParameter("age").trim();
		String gender=request.getParameter("gender").trim();
		String userName=request.getParameter("username").trim();
		String password=request.getParameter("password").trim();
			
		//Perform data conversions
			
		int ageUser=-1;
			
		try  {
		ageUser=Integer.parseInt(age);
		}
		catch(NumberFormatException nfe) {
		errorMsgs.add("The age must be a positive integer");
		}
			
		//Verify form parameters
			
		if(firstName.length()==0)  {
		errorMsgs.add("Please enter the first name");
		}
		if(surname.length()==0)  {
		errorMsgs.add("Please enter the surname");
		}
		if(age.length()==0)  {
		errorMsgs.add("Please enter the age");
		}
		if(userName.length()==0)  {
		errorMsgs.add("Please enter the username");
		}
		if(password.length()==0)  {
		errorMsgs.add("Please enter the password");
		}
			
		//Perform business logic
		User user =new User(firstName,surname,ageUser,gender,userName,password);
			
		//Store the new user in the request-scope
		request.setAttribute("user", user);
                
		//Send the ErrorPage view if there were errors
		if(!errorMsgs.isEmpty())  {
		RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
		view.forward(request, response);
		return;
		}
                
		//Store the new user into the database
		try {
		//Class.forName("com.mysql.jdbc.Driver");
		//con =DriverManager.getConnection 
		//("jdbc:mysql://127.0.0.1:3306/library","root","root");
                
                String host="jdbc:derby://localhost:1527/Libreria";
                String name="root";
                String pass="root";
                con =DriverManager.getConnection(host, name, pass);
                //statement=con.createStatement();
		//pstmt = con.prepareStatement("insert into Users values(‘c’,’c’,12,’c’,’c’,’c’)");
                /*
		pstmt.setString(1,firstName);
		pstmt.setString(2,surname);
		pstmt.setString(3,age);
		pstmt.setString(4,gender);
               sd pstmt.setString(5,userName);
		pstmt.setString(6,password);*/
		//pstmt.executeUpdate();	
		Statement stmt=(Statement)con.createStatement();
                String insert="Insert into users values ('"+firstName+"','"+surname+"',"
                        +age+",'"+gender+"','"+userName+"','"+password+"') ";
                stmt.executeUpdate(insert);		  	
		//Send the success view
		RequestDispatcher view = request.getRequestDispatcher("successUser.jsp");
		view.forward(request, response);
		return;
					
				}
		catch(SQLException e) {
		errorMsgs.add(e.getMessage());
			//dispatch to the ErrorPage
                 RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
		e.printStackTrace();
		view.forward(request, response);					 }
			}

		//Handle any unexpected exceptions
		catch(RuntimeException e)  {
			errorMsgs.add(e.getMessage());
			//dispatch to the ErrorPage
			RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
			view.forward(request, response);
	                }//END of try-catch block
	        }//END of doPost method

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
