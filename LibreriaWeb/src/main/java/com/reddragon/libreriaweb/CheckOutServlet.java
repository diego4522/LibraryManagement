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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServletPath"})
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
        // TODO Auto-generated method stub
        // Keep a set of strings to record form processing errors.
        List<String> errorMsgs = new ArrayList<String>();

        // JDBC variables
        Connection con = null;
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);

        try {

            // Retrieve form parameters

            String userName = request.getParameter("username");
            String checkout_book = request.getParameter("checkout");
            String return_book = request.getParameter("return");

            String id = request.getParameter("bkgroup1");
            int book_id = Integer.parseInt(id);
            if (userName.length() == 0) {
                errorMsgs.add("Please enter the user name");
            }
            if (id.length() == 0) {
                errorMsgs.add("Please click on the book you want to checkout");
            }

            // Checking out a book
                            String host="jdbc:derby://localhost:1527/Libreria";
                            String name="root";
                            String pass="root";
                            con =DriverManager.getConnection(host, name, pass);
            //Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "root");

            int userExists = 0, bookIssued = 0;


            if (checkout_book == null && return_book == null) {
                errorMsgs.add("Please select either checkout/return option!");
            }
            if (checkout_book != null || return_book != null) {
                userExists = userExists(con, userName);

                if (userExists == 0) {
                    errorMsgs.add("User does not exist in system");
                } else {
                    bookIssued = bookAlreadyIssued(con, userName, book_id);
                }

                if (bookIssued == 1) {
                    errorMsgs.add("The same user has already borrowed this book!");
                }

                if (userExists == 1 && checkout_book != null) {
                    checkout(con, book_id, bookIssued, request, userName, response, errorMsgs);
                }
                else if (userExists == 1 && return_book != null) {
                    returnBook(con, book_id, bookIssued, userName, request, response, errorMsgs);
                }
            }

            // Send the ErrorPage view if there were errors
            if (!errorMsgs.isEmpty()) {
                setBooksIntoRequest(con, request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int userExists(Connection con, String userName) throws SQLException {
        PreparedStatement stmt = null;

        stmt = con.prepareStatement("select username from Users where username=?");
        stmt.setString(1, userName);
        ResultSet rsUser = stmt.executeQuery();
        int userExists = 0;
        if (rsUser.next()) {
            userExists = 1;
        }
        return userExists;
    }

    private int bookAlreadyIssued(Connection con, String userName, int book_id) throws SQLException {
        PreparedStatement pst;
        ResultSet rsBooks;
        int bookAlreadyIssued = 0;
        pst = con.prepareStatement("select book_id from Checkout where Username=? and book_id=?");
        pst.setString(1, userName);
        pst.setInt(2, book_id);
        rsBooks = pst.executeQuery();
        if (rsBooks.next()) {            
            bookAlreadyIssued = 1;
        }
        return bookAlreadyIssued;
    }

    private void incrementAvailableCopies(int availcopies, Connection con, int book_id) throws SQLException {
        PreparedStatement pst4 = null;
        availcopies += 1;
        pst4 = con.prepareStatement("update Books set availablecopies = "+availcopies+" where id= "+book_id+"");
        //pst4.setInt(1, availcopies);
        //pst4.setInt(2, book_id);

        pst4.executeUpdate();
        // Send the success view
    }

    private void decrementAvailableCopies(int oldcopies, Connection con, int book_id) throws SQLException {
        PreparedStatement pst4 = null;
        oldcopies -= 1;
         pst4 = con.prepareStatement("update Books set availablecopies = "+oldcopies+" where id= "+book_id+"");
        //pst4.setInt(1, availcopies);
        //pst4.setInt(2, book_id);
        pst4.executeUpdate();
    }

    private void insertIntoCheckout(Connection con, int book_id, String userName, String returndate) throws SQLException, ParseException {
        PreparedStatement pst2 = null;
        //pst2 = con.prepareStatement("insert into Checkout values(null,?,?,?)");
        pst2 = con.prepareStatement("insert into Checkout(book_id,username,return_date) values"
                + "("+book_id+",'"+userName+"','"+new java.sql.Date(getDateFormat().parse(returndate).getTime())+"')");
        //pst2.setInt(1, book_id);
        //pst2.setString(2, userName);
        //pst2.setDate(3, new java.sql.Date(getDateFormat().parse(returndate).getTime()));
        pst2.executeUpdate();
    }

    private void setCheckoutIntoRequest(int book_id, String userName, HttpServletRequest request) {
        CheckOut checkOut = new CheckOut();
        checkOut.setBookId(book_id);
        checkOut.setUserName(userName);
        request.setAttribute("checkout", checkOut);
    }

    private void removeFromCheckout(Connection con, String username, int book_id) throws SQLException {
        PreparedStatement pst2 = null;
        pst2 = con.prepareStatement("delete from Checkout where username= '"+username+"' and book_id= "+book_id+"");
        //pst2.setString(1, username);
        //pst2.setInt(2, book_id);
        pst2.executeUpdate();
    }

    private void setReturnIntoRequest(int book_id, String userName, HttpServletRequest request) {
        CheckOut checkOut = new CheckOut();
        checkOut.setBookId(book_id);
        checkOut.setUserName(userName);
        request.setAttribute("returnBook", checkOut);
    }

    private SimpleDateFormat getDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("MM/dd/yy");
        return dateFormat;
    }

    private void setBooksIntoRequest(Connection con, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Statement statement = con.createStatement();
        ResultSet rsBookList = statement.executeQuery("SELECT * FROM Books");

        List<Book> books = new ArrayList<Book>();
        while (rsBookList.next()) {
            Book book = new Book();
            book.setBookId(rsBookList.getInt("id"));
            book.setBookName(rsBookList.getString("bookName"));
            book.setAuthorName(rsBookList.getString("authorName"));
            book.setISBN(rsBookList.getString("ISBN"));
            book.setPublisher(rsBookList.getString("publisher"));
            book.setTotalCopies(rsBookList.getInt("totalcopies"));
            book.setAvailCopies(rsBookList.getInt("availablecopies"));
            books.add(book);
        }

        request.setAttribute("books", books);
        RequestDispatcher reqDispatcher = request.getRequestDispatcher("checkOut.jsp");
        reqDispatcher.forward(request, response);
    }

    private void checkout(Connection con, int book_id, int bookIssued, HttpServletRequest request, String userName, HttpServletResponse response, List<String> errorMsgs) throws SQLException, IOException, ParseException, ServletException {
        ResultSet rsBookById;
        int availableCopies;
        PreparedStatement pst = null;
        pst = con.prepareStatement("select availablecopies from Books where id= "+book_id+"");
        //pst.setInt(1, book_id);
        rsBookById = pst.executeQuery();
        if (rsBookById.next()) {
            availableCopies = rsBookById.getInt(1);
            if (availableCopies > 0) {
                if (bookIssued == 0) {
                    String returndate = request.getParameter("dateofreturn");
                    insertIntoCheckout(con, book_id, userName, returndate);
                    decrementAvailableCopies(availableCopies, con, book_id);
                    setCheckoutIntoRequest(book_id, userName, request);
                    // Send the success checkout view
                    RequestDispatcher view = request
                            .getRequestDispatcher("successCheckout.jsp");
                    view.forward(request, response);
                }
            } else {
                errorMsgs.add("There are no copies of the requested book available.");
            }
        }
    }

    private void returnBook(Connection con, int book_id, int bookIssued, String userName, HttpServletRequest request, HttpServletResponse response, List<String> errorMsgs) throws IOException, SQLException, ServletException {
        ResultSet rsBooks;
        int totcopies;
        int availcopies;
        PreparedStatement pst = null;
        pst = con.prepareStatement("select totalcopies,availablecopies from Books where id= "+book_id+"");
        //pst.setInt(1, book_id);
        rsBooks = pst.executeQuery();
        if (rsBooks.next()) {
            totcopies = rsBooks.getInt(1);
            availcopies = rsBooks.getInt(2);
            if ((availcopies + 1) <= totcopies) {
                if (bookIssued == 1) {
                    removeFromCheckout(con, userName, book_id);
                    incrementAvailableCopies(availcopies, con, book_id);
                    setReturnIntoRequest(book_id, userName, request);
                    RequestDispatcher view = request.getRequestDispatcher("successReturn.jsp");
                    view.forward(request, response);
                } else {
                    errorMsgs.add("The given user has not borrowed this book!");
                }
            } else {
                errorMsgs.add("Available copies of the book cannot exceed total copies.");
            }
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
