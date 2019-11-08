package buyOwned;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;




/**
 * Servlet implementation class Uploadbook
 */
@WebServlet("/Uploadbook")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 *50)
public class Uploadbook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		HttpSession tu=request.getSession(false);
		User uob=(User) tu.getAttribute("uob");
		ServletContext con=getServletContext();
		Database ob=(Database)con.getAttribute("obj");
		
		String bookname=request.getParameter("bookname");
		String authorname=request.getParameter("authorname");
		String description=request.getParameter("description");
		String bcondition=request.getParameter("group1");
		
		int price = Integer.parseInt(request.getParameter("price"));
		
		
		int sno=ob.maxbooksno();
		sno++;
		
	    Part part1 = request.getPart("front");
	    Part part2 = request.getPart("back");
	    Part part3 = request.getPart("index");
		
		if(ob.insertbook(sno, uob.getSno(), bookname, authorname, description, price, bcondition, part1, part2, part3))
		{
			if(ob.addresscheck(uob.getSno()))
			{
			String street=request.getParameter("street");
			String city=request.getParameter("city");
			String district=request.getParameter("district");
			String pincode=request.getParameter("pincode");
			ob.insertaddress(uob.getSno(), street, city, district, pincode);
			}
			
			PrintWriter out=response.getWriter();
			RequestDispatcher rd=request.getRequestDispatcher("msg.html");
			rd.include(request, response);
			
			out.println("<div class=\"row\">\r\n" + 
					"        <div class=\"col m7 s12\">\r\n" + 
					"            <img src=\"images/login.svg\" class=\"login-image\">\r\n" + 
					"        </div>\r\n" + 
					"        <div class=\"col m5 s12\">\r\n" + 
					"            <div class=\"form-container\">\r\n" + 
					"                <center><a href=\"index.jsp\"><img src=\"./images/logo_black.png\" class=\"logo-login\" ></a></center>\r\n" + 
					"                \r\n" + 
					"                <h5 class=\"msg1\">Book Uploaded<br>Successfully</h5>\r\n"	+ 
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </div>");
		
		}
		else 
		{
			PrintWriter out=response.getWriter();
			RequestDispatcher rd=request.getRequestDispatcher("msg.html");
			rd.include(request, response);
			
			out.println("<div class=\"row\">\r\n" + 
					"        <div class=\"col m7 s12\">\r\n" + 
					"            <img src=\"images/login.svg\" class=\"login-image\">\r\n" + 
					"        </div>\r\n" + 
					"        <div class=\"col m5 s12\">\r\n" + 
					"            <div class=\"form-container\">\r\n" + 
					"                <center><a href=\"index.jsp\"><img src=\"./images/logo_black.png\" class=\"logo-login\" ></a></center>\r\n" + 
					"                \r\n" + 
					"                <h5 class=\"msg2\">Error Occur</h5>\r\n"	+ 
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </div>");
		}
	}
}
