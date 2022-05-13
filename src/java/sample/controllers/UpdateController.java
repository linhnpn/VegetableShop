/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.product.ProductError;
import sample.utils.DBUtils;

/**
 *
 * @author linhn
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String ERROR = "MainController?action=Search&search=";
    private static final String SUCCESS = "MainController?action=Search&search=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        try {
            boolean checkValidation = true;
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String image = request.getParameter("image");
            String categoryID = request.getParameter("categoryID");
            double price = Double.parseDouble(request.getParameter("price"));
            double quantity = Double.parseDouble(request.getParameter("quantity"));
            Date importDate = DBUtils.getDate(request.getParameter("importDate"));
            Date usingDate = DBUtils.getDate(request.getParameter("usingDate"));
            ProductDTO product = new ProductDTO(productID, productName, image, categoryID, price, quantity, importDate, usingDate);
            ProductDAO dao = new ProductDAO();
            if (productName.length() < 2 || productID.length() > 50) {
                productError.setProductNameError("ProductName must be in [2, 50]");
                checkValidation = false;
            }
            if (image.length() < 1 || image.length() > 1500) {
                productError.setImageError("Image must be in [1, 1500]");
                checkValidation = false;
            }

            if (price > 500000 || price < 1000) {
                productError.setPriceError("Price must be in [1000, 500000]");
                checkValidation = false;
            }
            if (quantity > 100 || quantity < 0) {
                productError.setQuantityError("Quantity must be in [0, 100]");
                checkValidation = false;
            }
            if (importDate.compareTo(usingDate) > 0) {
                productError.setImportDateError("importDate is greater than usingDate!!");
            }
            java.util.Date date = new java.util.Date();
            if (date.compareTo(usingDate) > 0) {
                productError.setUsingDateError("Out of expiry date!!");
                checkValidation = false;
            }
            if (checkValidation) {
                if (dao.update(product)) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
