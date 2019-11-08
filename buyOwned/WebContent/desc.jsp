<%@page import="buyOwned.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="buyOwned.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="desc.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.css"
        integrity="sha256-a2tobsqlbgLsWs7ZVUGgP5IvWZsx8bTNQpzsqCSm5mk=" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.2/css/all.css">
    <link rel="icon" href="./images/favicon.png">
    <link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="materialize/js/materialize.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <title>'Book name here'</title>
</head>

<body>
<%! int sno;
	User uob;
	Database obj;
    ResultSet rs,rseller;%>
<% 
    uob=(User)session.getAttribute("uob");
	if(uob==null)
	{
		response.sendRedirect("login.html");
	}
	sno=Integer.parseInt(request.getParameter("sno"));
	obj=(Database)application.getAttribute("obj");
	rs=obj.GetBook(sno);
	rs.next();
	rseller=obj.GetSellerinfo(rs.getInt(2));
	rseller.next();
	application.setAttribute("rsbook", rs);%>

    <div class="header scroll" id="head">
        <input type="checkbox" id="chk">
        <label for="chk" class="show-menu">
            <i class="fas fa-bars"></i>
        </label>

        <img src="images/logo_black.png" class="left" height="90" width="90">

        <ul class="menu">
          	<%if (uob!=null)
            {
        	 %>
            <a><%=uob.getName() %></a>
            <a href="upload.jsp">Upload</a>
            <%} %>
            <a href="#" class="home">Home</a>
            <a href="#" class="contact"> Contact</a>
            
            <%if (uob!=null)
            {
        	 %>
            <a href="">Logout</a>
            <%} %>

            <label for="chk" class="hide-menu">
              <i class="fas fa-times"></i>
            </label>
          </ul>
    </div>
    <br><br><br><br>
    <div class="row">
        <div class="col m6 s12 product-container carousel carousel-slider">
            <a class="carousel-item center" href="#one!"><img src="Getbookimage?ibook=front" class="materialboxed" height="500"></a>
            <a class="carousel-item center" href="#two!"><img src="Getbookimage?ibook=back" class="materialboxed" height="500"></a>
            <a class="carousel-item center" href="#three!"><img src="Getbookimage?ibook=indexpage" class="materialboxed" height="500"></a>
        </div>
        <div class="col m5 s12 product-details">
            <h3 class="bookname"><%=rs.getString(3) %></h3>
            <h6 class="authorname">By <%=rs.getString(4) %></h5>
                <br>
                <hr><br>
                <div class="price">
                    <b>₹<%=rs.getInt(6)%>.00</b>
                </div>
                <div class="details"><%=rs.getString(5)%></div><br>
                <hr>
                <br>
                <p class="details">Condition : <%=rs.getString(7)%></p>
        </div>
    </div>
    <div class="row seller-desc">
        <div class="col m12 s12">
            <h4>Seller Description</h4>
            <h6>Posted By : <%=rseller.getString(1)%></h6><br>
            <h6>Mobile No. : <%=rseller.getString(2)%></h6><br>
            <h6><b>Address &nbspDetails</b></h6><br>
            <p>
            	Street: &nbsp <%=rseller.getString(4)%><br>
                City: &nbsp <%=rseller.getString(5)%><br>
                District: &nbsp <%=rseller.getString(6)%><br>
                Pincode: &nbsp <%=rseller.getString(7)%><br>
            </p><br>
        </div>
    </div>
    <div class="footer">

        © Designed With <i class="material-icons footer-icon"> favorite</i> by silverfang

    </div>
</body>
<script>

    $('.carousel.carousel-slider').carousel({
        fullWidth: true,
        indicators: true
    });
    $(document).ready(function () {
        $('.materialboxed').materialbox();
    });
</script>

</html>