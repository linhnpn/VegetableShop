<%-- 
    Document   : viewCart
    Created on : Mar 3, 2022, 8:38:24 PM
    Author     : linhn
--%>

<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <link rel="stylesheet" href="css/styleCart.css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <div class="container body-shop">
            <div class="row">
                <h1>Shopping Cart:</h1>
            </div>

            <%
                String error = (String) request.getAttribute("ERROR");
                if (error == null) {
                    error = "";
                }
            %>
            <div class="row">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Name</th>                                
                                <th>Quantity</th>
                                <th class="text-center">Price</th>
                                <th class="text-center">Total</th>
                                <th>Remove</th>
                                <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Cart cart = (Cart) session.getAttribute("CART");
                                if (cart != null) {
                                    if (cart.getCart().size() > 0) {
                            %>



                            <%
                                double total = 0;
                                for (ProductDTO product : cart.getCart().values()) {
                                    total += product.getPrice() * product.getQuantity();
                            %>
                        <form action="MainController">


                            <tr>                                
                                <td class="col-sm-8 col-md-6">                                   
                                    <div class="media">
                                        <a class="thumbnail pull-left" href="#"> <img class="media-object" src="<%= product.getImage()%>" style="width: 72px; height: 72px;"> </a>
                                        <div class="media-body">
                                            <h4 class="media-heading"><a href="#"><%= product.getProductName()%></a></h4>                                          

                                        </div>
                                    </div>
                                    <input type="hidden" name="id" value="<%= product.getProductID()%>"/>

                                </td>
                                <td class="col-sm-2 col-md-2" style="text-align: center; margin: auto 0px">
                                    <input class="form-control" type="number" name="quantity" value="<%= product.getQuantity()%>" min="0.1" step="0.1" required=""/>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center">
                                    <strong><%= product.getPrice()%> VND</strong>                                    
                                    <input type="hidden" name="price" value="<%= product.getPrice()%>"</td>

                                <td class="col-sm-1 col-md-1 text-center"><strong><%= product.getQuantity() * product.getPrice()%> VND</strong></td>
                                <!--remove-->
                                <td class="col-sm-2 col-md-2">
                                    <input type="submit" name="action" class="btn btn-danger" value="Remove"/>
                                </td>
                                <td class=""col-sm-2 col-md-2>
                                    <input type="submit" name="action" class="btn btn-warning" value="Edit"/>
                                </td>

                            </tr>

                        </form>
                        <%
                            }
                        %>
                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td>   </td>
                            <td>   </td>
                            <td><h5>Subtotal</h5></td>
                            <td class="text-right"><h5><strong><%= total%> VND</strong></h5></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>   </td>
                            <td>   </td>
                            <td>   </td>
                            <td><h5>Estimated shipping</h5></td>
                            <td class="text-right"><h5><strong>0 VND</strong></h5></td>
                        </tr>
                        <tr>
                            <td colspan="4"><span class="text-danger"><strong><%= error%></strong></span></td>
                            <td><h3>Total</h3></td>
                            <td class="text-right"><h3><strong><%= total%> VND</strong></h3></td>
                        </tr>                        
                        <tr>
                            <td></td>
                            <td>   </td>
                            <td>   </td>
                            <td>   </td>
                            <td>
                                <a href="MainController?action=Search&search=">
                                    <button type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                    </button>
                                </a>

                            </td>
                            <td>
                                <a href="MainController?action=Checkout">
                                    <button type="button" class="btn btn-success">
                                        Checkout <span class="glyphicon glyphicon-play"></span>
                                    </button>
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <%
                } else { %>
                <h2>(The List Is Empty)</h2>
                <a href="MainController?action=Search&search=">
                    <button type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                    </button>
                </a>
                <%

                    }%>

                <%
                } else { %>
                <h2>(The List Is Empty)</h2>
                <a href="MainController?action=Search&search=">
                    <button type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                    </button>
                </a>
                <%

                    }%>
            </div>

        </div>

        <div class="container">
            <div class="row footer">
                <div class="col-sm-7 text-center">

                    <div class="row">
                        <div class="col-sm-4">                            
                        </div>

                        <div class="col-sm-8"> 
                            <p>212 Le Van Viet, Dist. 9, HCMC</p>
                            <p>D29 Pham Van Dong, Cau Giay, Ha Noi</p>
                            <p>80 Truc Khe, Dong Da , Ha Noi</p>
                        </div>
                    </div>
                </div>

                <div class="col-sm-5 text-center">
                    <p>Phone Number | Email</p>

                    <p style="margin: auto" >1800 1525 | <a href="#">linhnguyen@gmail.com</a></p>
                </div>
            </div>
        </div>


    </body>
</html>
