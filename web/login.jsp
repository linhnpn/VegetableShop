<%-- 
    Document   : login
    Created on : Feb 28, 2022, 9:44:53 PM
    Author     : linhn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script src="https://www.google.com/recaptcha/api.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/styleLogin.css">
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        
        <div class="login">
            <div  class="login-triangle"></div>

            <h2 class="login-header">Log in</h2>

            <form action="MainController" method="POST" class="login-container">
                <p><input type="text" name="userID" placeholder="User ID" required=""></p>
                <p><input type="password" name="password" placeholder="Password" required="" ></p>
                <div class="g-recaptcha"
                     data-sitekey="6Lf62LceAAAAAEFZXm8CvX3AzInSVGxINAg1jtqy"></div>
                <p><input type="submit" name="action" value="Login"></p>
                <p class="error"><%= error%></p>
            </form>
        </div>        
        
    </body>
</html>
