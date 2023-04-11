

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccDelete
 */
public class AccDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccDelete() {
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
		
		String delacc = request.getParameter("delano");
		int dac = Integer.parseInt(delacc);
		
		try
		{
			Connection conn = ConnectDB.connect();
	    	Statement stmt3 = conn.createStatement();
	    	int rs = stmt3.executeUpdate("delete from accounts where id = '"+dac+"'");    
	    	if(rs == 1)
	    	{
	    		response.sendRedirect("delete.html");
	    	}
	    	else
	    	{
	    		response.sendRedirect("404.html");
	    	}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		
	}

}
