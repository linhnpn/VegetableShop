/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.user.UserDTO;

/**
 *
 * @author linhn
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private static final String ERROR_AD = "admin.jsp";
    private static final String ERROR_US = "user.jsp";
    private static final String SUCCESS_AD = "admin.jsp";
    private static final String SUCCESS_US = "user.jsp";
    private static final String AD = "AD";
    private static final String US = "US";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        String curUser = loginUser.getRoleID();
        String url = "";
        List<ProductDTO> listProduct = null;
        ProductDAO dao = new ProductDAO();
        if (AD.equals(curUser)) {
            url = ERROR_AD;
        } else if (US.equals(curUser)) {
            url = ERROR_US;
        }
        try {
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            if (AD.equals(curUser)) {
                listProduct = dao.getListProductV2(search);
            } else if (US.equals(curUser)) {
                listProduct = dao.getListProduct(search);
            }
            if (listProduct.size() > 0) {
                request.setAttribute("LIST_PRODUCT", listProduct);
                if (AD.equals(curUser)) {
                    url = SUCCESS_AD;
                } else if (US.equals(curUser)) {
                    url = SUCCESS_US;
                }
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
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
