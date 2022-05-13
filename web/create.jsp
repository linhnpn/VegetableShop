<%-- 
    Document   : create
    Created on : Mar 3, 2022, 10:00:10 AM
    Author     : linhn
--%>

<%@page import="sample.product.ProductError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <h1>Create new Product</h1>
        <%                         
            ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
            if (productError == null) {
                productError = new ProductError();
            }
        %>
        <form action="MainController" method="POST">
            Product ID <input type="text" name="productID" required="" />
            <%= productError.getProductIDError()%></br>
            Product Name <input type="text" name="productName" required="" />
            <%= productError.getProductNameError()%></br>
            Image URL<input type="text" name="image" required="" />
            <%= productError.getImageError()%></br>
            Category ID <select name="categoryID">
                <option value="RX">Rau Xanh</option>
                <option value="RS">Rau Song</option>
                <option value="UC">Cu</option>
                <option value="QU">Qua</option>
            </select>
            <%= productError.getCategoryIDError()%></br>
            Price <input type="number" name="price" step="1000" required=""/>
            <%= productError.getPriceError()%></br>
            Quantity <input type="number" name="quantity" step="0.1" required=""/>
            <%= productError.getQuantityError()%></br>
            Import Date <input type="date" name="importDate" required=""/>
            <%= productError.getImportDateError()%></br>
            Using Date <input type="date" name="usingDate" required=""/>
            <%= productError.getUsingDateError()%></br>
            <input type="submit" name="action" value="Create"/>
            <input type="reset" value="Reset"/>
        </form>
        <a href="SearchController">Manager Product</a>
    </body>
</html>
