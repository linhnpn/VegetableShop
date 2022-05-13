/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.utils.VerifyRecaptcha;

/**
 *
 * @author linhn
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String AD = "AD";
    private static final String ADMIN_PAGE = "MainController?action=Search&search=";
    private static final String US = "US";
    private static final String USER_PAGE = "MainController?action=Search&search=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            //reCaptcha
            String gRecaptchaResponse = request
                    .getParameter("g-recaptcha-response");
            System.out.println(gRecaptchaResponse);
            boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            if (!verify) {
                out.println("<font color=red>You missed the Captcha.</font>");
            }
//            //end Recaptcha
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(userID, password);

            if (loginUser != null && verify == true) {
                String roleID = loginUser.getRoleID();
                HttpSession session = request.getSession();
                if (null == roleID) {
                    request.setAttribute("ERROR", "Your role is not support!");
                } else {
                    switch (roleID) {
                        case AD:
                            session.setAttribute("LOGIN_USER", loginUser);
                            url = ADMIN_PAGE;
                            break;
                        case US:
                            session.setAttribute("LOGIN_USER", loginUser);
                            url = USER_PAGE;
                            break;
                        default:
                            request.setAttribute("ERROR", "Your role is not support!");
                            break;
                    }
                }
            } else if (loginUser == null && verify == true) {
                request.setAttribute("ERROR", "Incorrect userID or password!");
            } else {
                request.setAttribute("ERROR", "Missed the Captcha");
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
