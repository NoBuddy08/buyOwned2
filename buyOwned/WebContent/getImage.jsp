
<%@page import="buyOwned.Database"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!
   ResultSet rs;%>
<%

        rs=(ResultSet)application.getAttribute("rs");
    	Blob blob=rs.getBlob("front");
    	byte byteArray[]=blob.getBytes(1, (int)blob.length());
    	response.setContentType("image/gif");
    	OutputStream os=response.getOutputStream();
    	os.write(byteArray);
    	os.flush();
    	os.close();
    

%>
</body>
</html>