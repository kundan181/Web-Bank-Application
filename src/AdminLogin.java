

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String useremail = request.getParameter("logemail");
		String userpass = request.getParameter("logpass");
		
		try
		{
			Connection con = ConnectDB.connect();
			PreparedStatement ps1 = con.prepareStatement("select * from user where email =? and password =?");
			ps1.setString(1,useremail);
			ps1.setString(2,userpass);
			ResultSet rs = ps1.executeQuery();
			if(rs.next())
			{
				String tempem = rs.getString("email");
				String temppass = rs.getString("password");
				//response.sendRedirect("welcome.html");
				
				if(useremail.equals(tempem) && userpass.equals(temppass))
				{
					response.sendRedirect("createacc.html");
				}
			}
			else
			{
				response.sendRedirect("index.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
