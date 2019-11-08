package buyOwned;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Verifysecuritycode
 */
@WebServlet("/Verifysecuritycode")
public class Verifysecuritycode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext con=getServletContext();
		String sc1=request.getParameter("securitycode");
		String sc2=(String) con.getAttribute("securitycode");
		
		
		if(sc1.equals(sc2))
		{
			PrintWriter out=response.getWriter();
			RequestDispatcher rd=request.getRequestDispatcher("msg.html");
			rd.include(request, response);
		
			Database ob=(Database)con.getAttribute("obj");
			ob.insert((User)con.getAttribute("ob"));

			out.println("<div class=\"row\">\r\n" + 
					"        <div class=\"col m7 s12\">\r\n" + 
					"            <img src=\"images/login.svg\" class=\"login-image\">\r\n" + 
					"        </div>\r\n" + 
					"        <div class=\"col m5 s12\">\r\n" + 
					"            <div class=\"form-container\">\r\n" + 
					"                <center><img src=\"./images/logo_black.png\" class=\"logo-login\" ></center>\r\n" + 
					"                \r\n" + 
					"                <h5 class=\"msg1\">E-mail Verified<br>SignUp Successful</h5>\r\n"	+ 
					"                <h5 class=\"msg3\">Redirecting to Login Page...</h5>" + 
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </div>");
			out.println("<meta http-equiv='refresh' content='4;URL=login.html'>");
			
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
					"                <center><img src=\"./images/logo_black.png\" class=\"logo-login\" ></center>\r\n" + 
					"                \r\n" + 
					"                <h5 class=\"msg2\">Security Code Not Match<br><br>Verification Failed</h5>\r\n"  +
					"                <h5 class=\"msg3\">Redirecting to Signup Page...</h5> "  +
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </div>");
			out.println("<meta http-equiv='refresh' content='4;URL=signup.html'>");
		}
	}

}
