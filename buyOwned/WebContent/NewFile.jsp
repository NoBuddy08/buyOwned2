<%@page import="java.sql.ResultSet"%>
<%@page import="buyOwned.Database"%>
<%@page import="buyOwned.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.css"
    integrity="sha256-a2tobsqlbgLsWs7ZVUGgP5IvWZsx8bTNQpzsqCSm5mk=" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.2/css/all.css">
  <link rel="icon" href="./images/favicon.png">
  <link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="materialize/js/materialize.js"></script>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Sacramento&display=swap" rel="stylesheet">
  <title>buyOwned</title>
</head>

<body>
<%!User uob;
   Database obj;
   ResultSet rs;%>
<% uob=(User)session.getAttribute("uob");
   obj=(Database)application.getAttribute("obj");
   rs=obj.GetBooks();%>
  <div class="container-fluid">
    <div class="col m12">
      <div class="dark">
        <div class="intro-image-container row">
          <img src="images/intro.svg" class="intro-image col m5 s12">

          <div class="col m5 s12 offset-m2 quote-container">
            <span class="quote">“A room without books is like a body without a soul.”</span>
            <div class="row">
              <p style="float:right">― Marcus Tullius Cicero</p>
            </div>
          </div>
        </div>
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
            <%if (uob==null)
            {
        	 %>
            <a href="#" class="home">Home</a>
            <a href="login.html" class="login">Login</a>
            <a href="signup.html" class="signup">Signup</a>
            <%} %>
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
      </div><br>
      <div class="row buttons">

        <a href="upload.html"><button
            class="col m4 s10 offset-s1 btn waves-effect waves-dark sell black-text white z-depth-2 fill">Sell</button></a>
        <a href="donate.html"><button class="col m4 s10 offset-s1 btn donate  red lighten-2 animated bounce infinite">Donate
            ^_^</button></a>

      </div>


      <div class="carousel carousel-slider">
        <!-- href ME DESC PAGE  -->
        <a class="carousel-item" href="#one!"><img class="amber" src="" width="100" height="500"></a>
        <a class="carousel-item" href="#two!"><img class="red" src="" width="100" height="500"></a>
        <a class="carousel-item" href="#three!"><img class="blue" src="" width="100" height="500"></a>
        <a class="carousel-item" href="#four!"><img class="green" src="" width="100" height="500"></a>

      </div>


      <div class="row items">
        <%if(rs.next())
    	  {application.setAttribute("rs", rs);%>
        <div class="product-container col s12 m3">

          <div class="card sticky-action">
            <div class="card-image waves-effect waves-block waves-light">
              <img class="activator" src="Getimage" width="100">
            </div>
            <div class="card-content">
              <span class="card-title activator grey-text text-darken-4"><%=rs.getString(3)%><i
                  class="material-icons right">more_vert</i></span>
              <p>
                <div class="card-action">
                  <a href="desc.html"><button class="details btn waves-effect waves-light waves-block z-depth-1">See
                      Details</button></a>
                </div>
            </div>
            <div class="card-reveal">
              <span class="card-title grey-text text-darken-4"><%=rs.getString(3)%><i
                  class="material-icons right">close</i></span>
              <p><%=rs.getString(5)%></p>
            </div>
          </div>
        </div>
        <%} %>

      </div>


      <div class="footer">

        © Designed With <i class="material-icons footer-icon"> favorite</i> by silverfang

      </div>

    </div>
</body>
<script>
  $('.carousel.carousel-slider').carousel({
    fullWidth: true,
    indicators: true
  });

  var nav = document.querySelector('.header'); // Identify targe
  var menu = document.querySelectorAll('.menu a');
  nav.classList.remove("scroll");
  for (let i = 0; i < menu.length; i++)
    menu[i].style.color = "white";
  window.addEventListener('scroll', function (event) { // To listen for event


    if (window.scrollY <= 85) { // Just an example
      nav.classList.remove("scroll"); // or default color
      for (let i = 0; i < menu.length; i++)
        menu[i].style.color = "white";
    } else {
      nav.classList.add("scroll"); // or default color
      for (let i = 0; i < menu.length; i++)
        menu[i].style.color = "black";
    }
  });
</script>

</html>