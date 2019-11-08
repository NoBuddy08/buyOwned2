package buyOwned;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Getimage
 */
@WebServlet("/Getimage")
public class Getimage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Getimage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultSet rs;
		ServletContext con=getServletContext();
		//Database obj=(Database)con.getAttribute("obj");
		rs=(ResultSet)con.getAttribute("rs");

		      
		    	Blob blob;
				try {
					blob = rs.getBlob("front");
					byte byteArray[]=blob.getBytes(1, (int)blob.length());
					response.setContentType("image/gif");
			    	OutputStream os=response.getOutputStream();
			    	os.write(byteArray);
			    	os.flush();
			    	os.close();
			    	rs.next();
			    	con.setAttribute("rs", rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
