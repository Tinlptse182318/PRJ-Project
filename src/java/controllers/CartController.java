/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.Account;
import db.Product;
import db.ProductFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.Item;

/**
 *
 * @author PHT
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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

        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");

        switch (action) {
            case "index":
                index(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "empty":
                empty(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "checkout":
                checkout(request, response);
                break;
            case "thanks":
                thanks(request, response);
                break;
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String layout = (String) request.getAttribute("layout");
        request.getRequestDispatcher(layout).forward(request, response);
    }
    
    protected void thanks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String layout = (String) request.getAttribute("layout");
        request.getRequestDispatcher(layout).forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String layout = (String) request.getAttribute("layout");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            //lay thong tin product
            ProductFacade pf = new ProductFacade();
            Product product = pf.select(id);
            //lay tham chieu cua session
            HttpSession session = request.getSession();
            //lay cart tu session
            Cart cart = (Cart) session.getAttribute("cart");
            //neu trong session chua co cart
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            //them item vao cart            
            Item item = new Item(product, 1);
            cart.add(item);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Error when adding the product to cart.");
        }
        //quay ve home page
        //request.getRequestDispatcher("/").forward(request, response);
        request.getRequestDispatcher("/home/index.do").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay id tu client
        int id = Integer.parseInt(request.getParameter("id"));
        //lay cart tu session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //xoa item tuong ung id
        cart.remove(id);
        //cho hien trang /cart/index.do
        request.getRequestDispatcher("/cart/index.do").forward(request, response);
    }
    
    protected void empty(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay cart tu session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //xoa item tuong ung id
        cart.empty();
        //cho hien trang /cart/index.do
        request.getRequestDispatcher("/cart/index.do").forward(request, response);
    }
    
    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay data tu client
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //lay cart tu session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //xoa item tuong ung id
        cart.update(id, quantity);
        //cho hien trang /cart/index.do
        request.getRequestDispatcher("/cart/index.do").forward(request, response);
    }
    
    protected void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //kiem tra user login hay chua
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        //neu chua login 
        if(account == null){
            //luu url cua trang thanks vao session
            session.setAttribute("nextURL", "/cart/checkout.do");
            //chuyen user den trang login
            request.getRequestDispatcher("/account/login.do").forward(request, response);
        }else{//neu da login 
            //thuc hien thanh toan thanh toan
            //your code here
            //xoa sach gio hang
            Cart cart = (Cart) session.getAttribute("cart");
            cart.empty();
            //thi chuyen user den trang thanks
            request.getRequestDispatcher("/cart/thanks.do").forward(request, response);
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
        processRequest(request, response);
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
