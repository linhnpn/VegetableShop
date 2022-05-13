<%-- Document : user Created on : Feb 16, 2022, 10:04:47 AM Author : linhn --%>

<%@page import="sample.product.ProductDTO" %>
<%@page import="java.util.List" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link rel="stylesheet" href="css/styleUser.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
              integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    </head>

    <body>
        <%
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }%>
        <div class="container-fluid mt-4 sticky-top">
            <nav class="navbar navbar-expand-lg navbar-secondary header bg-light">
                <a class="navbar-brand" href="#"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon" style="color: black;" ></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="MainController?action=Search&search=">Trang Chủ <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Liên Hệ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?action=View" aria-haspopup="true" aria-expanded="false">
                                Giỏ hàng của bạn  <span class='fas fa-cart-arrow-down' style='font-size:20px' ></span>
                            </a>                            
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link navbar-right" href="MainController?action=Logout">Đăng xuất  <span class="fas fa-power-off"></span></a>
                        </li>                      

                    </ul>                    
                </div>
            </nav>
        </div>  
        <div class="container">


            <div class="row">
                <div class="col-md-12 slide-show">
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="d-block w-100" src="https://sudospaces.com/babycuatoi/2021/01/chinh-sach-van-chuye-giao-hang.jpg" alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="https://www.reviewcathegioi.com/wp-content/uploads/2021/08/a.jpg" alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="https://media.suckhoecong.vn/thumb_x800x450/Images/Uploaded/Share/2016/03/12/nhung-loai-rau-cu-qua-an-toan-nhat-ma-khong-co-thuoc-tru-sau-doc-hai11457751979.jpg" alt="Third slide">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>

            </div>
            <div class="row" >
                <div class="col-md-12">
                    <form action="MainController" class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                            <input type="text" name="search" class="form-control" placeholder="Search..." value="<%= search%>">
                            <input type="submit" class="button" name="action" value="Search"/>
                        </div>

                    </form>
                </div>
            </div>
            <div class="row">

                <% List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
                    if (listProduct != null) {
                        if (listProduct.size() > 0) {
                %>           


                <%                     for (ProductDTO product : listProduct) {%>
                <div class="col-md-4 col-xs-6 product">
                    <div class="element" >
                        <form action="MainController">

                            <div>
                                <input type="hidden" name="image" value="<%= product.getImage()%>"/>
                                <img src="<%= product.getImage()%>" class="img-thumbnail img-fluid"/>


                            </div>
                            <div class="product-name" >
                                <input type="hidden" name="productID"
                                       value="<%= product.getProductID()%>" readonly="" />
                                <input type="hidden" name="productName" class="text-center title"
                                       value="<%= product.getProductName()%>"
                                       readonly="" />
                                <%= product.getProductName()%>
                            </div>
                            <div>
                                <input class="product-price" type="hidden" name="price"
                                       value="<%= product.getPrice()%>" readonly="" />
                                <%= product.getPrice()%> VND/kg

                            </div>
                            <div>
                                <input class="product-quantity" type="number" name="quantity" step="0.1" value="0"
                                       required="" min="0.1" /> (kg)

                            </div>                      
                            <div>
                                <input type="submit" name="action" class="button-add" value="Add" />
                                <input type="hidden" name="search"
                                       value="<%= search%>" />
                            </div>
                        </form>
                    </div>                    

                </div>
                <% } %>

                <% }
                    }%>
            </div>
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