package buyOwned;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buyOwned.User;
import buyOwned.Sendmail;

/**
 * Servlet implementation class Emailverify
 */
@WebServlet("/Emailverify")
public class Emailverify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User ob=new User();
		ServletContext con=getServletContext();
		ob.setName(request.getParameter("name"));
		ob.setEmail(request.getParameter("email"));
		ob.setPassword(request.getParameter("password"));
		ob.setMob(request.getParameter("mobile"));
		
		con.setAttribute("ob", ob);
		
		String securitycode = Captcha.createcaptcha();
		con.setAttribute("securitycode", securitycode);
		
		Sendmail.sendemail(ob.getEmail(),"E-mail Verification", "Verification Security Code = "+securitycode+
				"\nThis is your one time Verification Code");
		
		RequestDispatcher rd=request.getRequestDispatcher("entersecuritycode.html");
		rd.include(request, response);
	}

}
