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
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		
		ServletContext con=getServletContext();
		Database ob=(Database)con.getAttribute("obj");
		
		if( ob.login(email, pass) )
		{
			User uob=ob.GetDetail();
			HttpSession tu=request.getSession();
			tu.setAttribute("uob", uob);
			response.sendRedirect("index.jsp");
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
					"                <h5 class=\"msg2\">Login Failed<br><br></h5>\r\n"  +
					"                <h5 class=\"msg3\">Redirecting to Login Page...</h5> "  +
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </div>");
			out.println("<meta http-equiv='refresh' content='4;URL=login.html'>");
		}
	}

}
