<%@page import="java.sql.ResultSet"%>
<%@page import="buyOwned.Database"%>
<%@page import="buyOwned.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.2/css/all.css">
    <link rel="icon" href="./images/favicon.png">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link href="upload.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="materialize/css/materialize.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Dropify/0.2.2/css/dropify.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="materialize/js/materialize.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Dropify/0.2.2/js/dropify.js"></script>

</head>

<body>
<%!User uob;
   Database obj;
   ResultSet rs;%>
<% uob=(User)session.getAttribute("uob");
   obj=(Database)application.getAttribute("obj");
   %>
    <div class="row container">
        <div class="form-container col m12 z-depth-2">
            <form action="Uploadbook" method="post"  enctype="multipart/form-data">


                <h4>Sell your Book</h4>
                <img src="./images/logo_black.png" class="logo-login">
                <br><br><br><br>
                <h5>Book Details</h5>
                <div class="row">
                    <div class="input-field col m6 s12">
                        <input type="text" id="bname" name="bookname">
                        <label for="bname">Bookname</label>
                    </div>
                    <div class="input-field col m6 s12">
                        <input type="text" id="authname" name="authorname">
                        <label for="authname">Author Name</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <textarea id="desc" name="description" class="materialize-textarea" data-length="250"
                            placeholder="Max. 250 characters"></textarea>
                        <label for="desc">Description</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 m6">
                        <input type="number" name="price" id="price" class="materialize-textarea"
                            placeholder="In Indian Rupees(INR)" min="10">
                        <label for="price">Price</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col m6 s12">
                        <p>What best describes the condition of the Book:</p><br>
                        <p>
                            <label>
                                <input class="with-gap" name="group1" type="radio" value="Excellent" />
                                <span>Excellent</span>
                            </label>
                        </p><br>
                        <p>
                            <label>
                                <input class="with-gap" name="group1" type="radio" value="Good" checked />
                                <span>Good</span>
                            </label>
                        </p><br>
                        <p>
                            <label>
                                <input class="with-gap" name="group1" type="radio" value="Average" />
                                <span>Average</span>
                            </label>
                        </p><br>
                        <p>
                            <label>
                                <input class="with-gap" name="group1" type="radio" value="Poor" />
                                <span>Poor</span>
                            </label>
                        </p><br>
                    </div>
                </div>
                <%if(obj.addresscheck(uob.getSno()))
                	{%>
                <h5>Personal Details</h5>
                <div class="row">
                    <div class="input-field col m6 s12">
                        <input type="text" id="street" name="street">
                        <label for="street">Street and House No.</label>
                    </div>
                    <div class="input-field col m6 s12">
                        <input type="text" id="city" name="city">
                        <label for="city">City</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col m6 s12">
                        <input type="text" id="district" name="district">
                        <label for="district">District</label>
                    </div>
                    <div class="input-field col m6 s12">
                        <input type="number" id="pincode" name="pincode">
                        <label for="pincode">Pincode</label>
                    </div>
                </div>
                <%} %>

                <h5>Upload Pictures</h5>
                <div class="row upload">
                    <div class="col m6 s12">
                        <label for="input-file-max-fs">Front View (Max 2MB)</label>
                        <input type="file" name="front" id="input-file-max-fs" class="dropify" data-max-file-size="2M" />
                    </div>
                    <div class="col m6 s12">
                        <label for="input-file-max-fs">Back View (Max 2MB)</label>
                        <input type="file" name="back" id="input-file-max-fs" class="dropify" data-max-file-size="2M" />
                    </div>
                </div>
                <div class="row upload">
                    <div class="col m6 s12">
                        <label for="input-file-max-fs">Index Page (Max 2MB)</label>
                        <input type="file" name="index" id="input-file-max-fs" class="dropify" data-max-file-size="2M" />
                    </div>
                </div>
                

                <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                    </button>
                <span class="terms">By submitting your Book for sale you agree to our <a href="#">Terms and Conditions</a></span>
            </form>
        </div>
    </div>
</body>
<script>
    $('.dropify').dropify();
    $(document).ready(function () {
        $('textarea#desc').characterCounter();
    });
</script>
</html>