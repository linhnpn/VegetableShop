/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.user.UserDTO;
import sample.shopping.Cart;

/**
 *
 * @author linhn
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "MainController?action=Mail";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ProductDAO dao = new ProductDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            double total = 0;
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    String orderID = (String) Integer.toString(dao.checkOrder());
                    java.util.Date date = new java.util.Date();
                    String orderDate = sdf.format(date);
                    boolean check = true;
                    UserDTO curUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    String error = "";
                    for (ProductDTO product : cart.getCart().values()) {
                        double quantity = dao.checkQuantity(product.getProductID());
                        if (quantity >= product.getQuantity()) {
                            total += product.getPrice() * product.getQuantity();

                        } else {
                            check = false;
                            error += product.getProductName() + " khong con du khoi luong trong kho. " + "SLCL: " + quantity + "</br>";
                        }
                    }
                    request.setAttribute("ERROR", error);
                    if (check) {
                        dao.addOrder(orderID, orderDate, Double.toString(total), curUser.getUserID());
                        int detailID = dao.checkDetail();
                        for (ProductDTO product : cart.getCart().values()) {
                            dao.addOrderDetail(Integer.toString(++detailID), Double.toString(product.getPrice()), Double.toString(product.getQuantity()), orderID, product.getProductID());
                            double quantity = dao.checkQuantity(product.getProductID());
                            double newQuantity = quantity - product.getQuantity();
                            dao.updateQuantity(Double.toString(newQuantity), product.getProductID());
                        }

                        session.removeAttribute("CART");
                        url = SUCCESS;
                    }
                }

            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
