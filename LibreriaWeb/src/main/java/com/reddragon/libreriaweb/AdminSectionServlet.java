/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reddragon.libreriaweb;

import com.reddragon.libreriaweb.model.Book;
import com.reddragon.libreriaweb.model.CheckOut;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AdminSectionServlet", urlPatterns = {"/AdminSectionServletPath"})
public class AdminSectionServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminSectionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminSectionServlet at " + request.getContextPath() + "</h1>");
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
       String menuSelection = request.getParameter("menuselection");
		
		if(menuSelection.equals("listbooks")){

                    try {
                          Connection connection = null;

                          Statement statement = null;

                          ResultSet rs = null,rs2 = null;
                         //Class.forName("com.mysql.jdbc.Driver").newInstance();
                            String host="jdbc:derby://localhost:1527/Libreria";
                            String name="root";
                            String pass="root";
                            connection =DriverManager.getConnection(host, name, pass);

                            statement = connection.createStatement();
                            rs = statement.executeQuery("SELECT * FROM Books");

                            List<Book> books = new ArrayList<Book>();
                            while(rs.next()){
                                    Book book = new Book();
                                    book.setBookId(rs.getInt("id"));
                                    book.setBookName(rs.getString("bookName"));
                                    book.setAuthorName(rs.getString("authorName"));
                                    book.setISBN(rs.getString("ISBN"));
                                    book.setPublisher(rs.getString("publisher"));
                                    book.setTotalCopies(rs.getInt("totalcopies"));
                                    book.setAvailCopies(rs.getInt("availablecopies"));
                                    books.add(book);
                            }
                            request.setAttribute("books", books);
                                                        
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("listBooks.jsp");
                            requestDispatcher.forward(request, response);
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }                    
                }else if(menuSelection.equals("listborrowedbooks")){
                    //String connectionURL = "jdbc:mysql://localhost:3306/library";

                    try {
                            Connection connection = null;

                            Statement statement = null;

                            ResultSet rs = null,rs2 = null;
                            String host="jdbc:derby://localhost:1527/Libreria";
                            String name="root";
                            String pass="root";
                            connection =DriverManager.getConnection(host, name, pass);

                            statement = connection.createStatement();
                            
                            
                            rs = statement.executeQuery("SELECT * FROM Checkout order by transaction_Id");
                            List<CheckOut> checkOut= new ArrayList<CheckOut>();
                            while(rs.next()){
                                CheckOut checkOutInstance = new CheckOut();
                                checkOutInstance.setBookId(rs.getInt("book_Id"));
                                checkOutInstance.setTransactionId(rs.getInt("transaction_Id"));
                                checkOutInstance.setUserName(rs.getString("username"));
                                checkOut.add(checkOutInstance);
                            }
                            request.setAttribute("checkout",checkOut);
                            
                            rs = statement.executeQuery("SELECT * FROM Checkout where return_date < CURRENT_DATE order by transaction_Id ");
                            List<CheckOut> past_returndate = new ArrayList<CheckOut>();
                            while(rs.next()){
                                CheckOut checkOutInstance = new CheckOut();
                                checkOutInstance.setBookId(rs.getInt("book_Id"));
                                checkOutInstance.setTransactionId(rs.getInt("transaction_Id"));
                                checkOutInstance.setUserName(rs.getString("username"));
                                past_returndate.add(checkOutInstance);
                            }
                            request.setAttribute("past_returndate", past_returndate);
                            
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("listBorrowedBooks.jsp");
                            requestDispatcher.forward(request, response);
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
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
