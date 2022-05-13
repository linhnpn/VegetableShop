<%-- 
    Document   : admin
    Created on : Feb 16, 2022, 10:08:36 AM
    Author     : linhn
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.ProductError"%>
<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        Welcome: <h1> <%= loginUser.getFullName()%></h1>      
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <form action="MainController">
            Search<input type="text" name="search" value="<%= search%>"/>
            <input type="submit" name="action" value="Search"/>
        </form>

        <%
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (listProduct != null) {
                if (listProduct.size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Import Date</th>
                    <th>Using Date</th>
                    <th>Category ID</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
                    if (productError == null) {
                        productError = new ProductError();
                    }
                %>
                <%= productError.getProductNameError()%>
                <%= productError.getImageError()%>
                <%= productError.getPriceError()%>
                <%= productError.getQuantityError()%>
                <%= productError.getImportDateError()%>
                <%= productError.getUsingDateError()%>
                <%
                    int count = 1;
                    for (ProductDTO product : listProduct) {
                %>



            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <input type="text" name="productID" value="<%= product.getProductID()%>" readonly=""/>

                    </td>
                    <td>
                        <input type="text" name="productName" value="<%= product.getProductName()%>" required=""/></br>

                    </td>
                    <td>
                        <input type="text" name="image" value="<%= product.getImage()%>" required=""/></br>
                        <img src="<%= product.getImage()%>" width="50px" height="50px"/>

                    </td>
                    <td>
                        <input type="number" name="price" step="1000" value="<%= product.getPrice()%>" required=""/></br>

                    </td>
                    <td>
                        <input type="number" step="0.1" name="quantity" value="<%= product.getQuantity()%>" required=""/></br>

                    </td>
                    <td>
                        <%
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        %>
                        <input type="date" name="importDate" value="<%= sdf.format(product.getImportDate())%>" required="" /></br>


                    </td>
                    <td>
                        <input type="date" name="usingDate" value="<%= sdf.format(product.getUsingDate())%>" required=""/></br>

                    </td>
                    <td>
                        <input type="text" name="categoryID" value="<%= product.getCategoryID()%>" readonly=""/>
                    </td>
                    <td>
                        <a href="MainController?action=Delete&productID=<%= product.getProductID()%>&search=<%= search%> ">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                </tr>
            </form>

            <%
                }
            %>

        </tbody>
    </table>

    <%
            }
        }
    %>

    <a href="create.jsp" >Create new Product</a>
</body>
</html>
