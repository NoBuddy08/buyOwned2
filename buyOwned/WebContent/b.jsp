<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
Connection con=null;
		PreparedStatement pt;
		ResultSet rs;
	%><%	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/buyowned","root","");
			
			pt=con.prepareStatement("select * from image");
           rs=pt.executeQuery();
           rs.next();
           Blob blob=rs.getBlob("image");
           byte byteArray[]=blob.getBytes(1, (int)blob.length());
           response.setContentType("image/gif");
           OutputStream os=response.getOutputStream();
           os.write(byteArray);
           os.flush();
           os.close();
			pt.executeUpdate();
			
			response.sendRedirect("b.jsp");
			
		}finally
		{
			
		}%>
</body>
</html>