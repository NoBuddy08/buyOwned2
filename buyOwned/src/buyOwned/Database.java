package buyOwned;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class Database {

	private Connection con=null;
	private PreparedStatement pt;
	private ResultSet rs;
	
	
	public Database()
	{
			try 
			{
				if(con==null)
				{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/buyowned","root","");
					System.out.println("Connection Established");
				}
				else
				{
					System.out.println("Connection already Established");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void insert(User ob)
	{
		int no;
		try 
		{
			 pt=con.prepareStatement("select max(sno) from users");
			 rs=pt.executeQuery();
			 rs.next();
			 if(rs.getInt(1)!=0)
			 {
				 no=rs.getInt(1);
				 no++;
			 }
			 else
			 {
				 no=1;
			 }
			
			pt=con.prepareStatement("insert into users values(?, ?, ?, ?, ?)");
			
			pt.setInt(1, no);
			pt.setString(2, ob.getName());
			pt.setString(3, ob.getEmail());
			pt.setString(4, ob.getPassword());
			pt.setString(5, ob.getMob());			
			
			pt.executeUpdate();
			
			System.out.println("Data Entered");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int maxbooksno()
	{
		int no = 0;
		
		try {
			pt=con.prepareStatement("select max(sno) from books");
			rs=pt.executeQuery();
			 rs.next();
			 if(rs.getInt(1)!=0)
			 {
				 no=rs.getInt(1);
				 return no;
			 }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return no;
	}
	public boolean insertbook(int sno,int uid,String bookname,String authorname,String description,int price,String bcondition,Part front,Part back,Part index)
	{
		
		try 
		{
			
			pt=con.prepareStatement("insert into books values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pt.setInt(1, sno);
			pt.setInt(2, uid);
			pt.setString(3, bookname);
			pt.setString(4, authorname);
			pt.setString(5, description);
			pt.setInt(6, price);
			pt.setString(7, bcondition);
			
			InputStream is=front.getInputStream();
			pt.setBlob(8, is);
			is=back.getInputStream();
			pt.setBlob(9, is);
			is=index.getInputStream();
			pt.setBlob(10, is);
			
			pt.executeUpdate();
			
			System.out.println("Book Entered");
			
			return true;
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean insertaddress(int uid,String street,String city,String district,String pincode)
	{
		try {
			pt=con.prepareStatement("insert into address values(?, ?, ?, ?, ?)");
			pt.setInt(1, uid);
			pt.setString(2, street);
			pt.setString(3, city);
			pt.setString(4, district);
			pt.setString(5, pincode);
			
            pt.executeUpdate();
			
			System.out.println("Address Entered");
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean login(String email,String password)
	{
		try {
			pt=con.prepareStatement("select * from users where email=? and password=?");
			pt.setString(1, email);
			pt.setString(2, password);
			
			rs=pt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	public User GetDetail()
	{
		User ob=new User();
		try {
			ob.setSno(rs.getInt(1));
			ob.setName(rs.getString(2));
			ob.setEmail(rs.getString(3));
			ob.setMob(rs.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ob;
	}
	public ResultSet GetBooks()
	{
		try {
			pt=con.prepareStatement("select * from books");
			rs=pt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet GetBook(int sno)
	{
		try {
			pt=con.prepareStatement("select * from books where sno=?");
			pt.setInt(1, sno);
			rs=pt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet GetSellerinfo(int sno)
	{
		try {
			pt=con.prepareStatement("select users.name,users.mob,address.* from users,address where users.sno=? and address.uid=?;");
			pt.setInt(1, sno);
			pt.setInt(2, sno);
			rs=pt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public boolean addresscheck(int sno)
	{
		try {
			pt=con.prepareStatement("select * from users where sno=?");
			pt.setInt(1, sno);
			rs=pt.executeQuery();
			if(rs.next())
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}