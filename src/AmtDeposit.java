

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AmtDeposit
 */
public class AmtDeposit extends HttpServlet {
	int newamt=0, depoacc;
	double obal,nbal;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmtDeposit() {
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
		
		String dacc = request.getParameter("dano");
		String damt = request.getParameter("damount");
		depoacc = Integer.parseInt(dacc);
		newamt = Integer.parseInt(damt);
		
		//check validation
		if(newamt>0)
		{	
			try
			{
				Connection conn = ConnectDB.connect();
		    	Statement stmt3 = conn.createStatement();
		    	ResultSet rs = stmt3.executeQuery("select * from accounts where id = '"+depoacc+"'");    
		    	while(rs.next())
		    	{
		    		obal = rs.getInt("balance"); 
		    	}
		    	
			    nbal = obal+newamt;
			    	
		    	int obj1 = stmt3.executeUpdate("update accounts set balance = '"+nbal+"' where id = '"+depoacc+"'");
		    	
		    	if(obj1==0)
		    	{
		    		response.sendRedirect("404.html");
		    	}
		    	else
		    	{
		    		response.sendRedirect("deposit.html");
		    	}
		    	ResultSet rs1 = stmt3.executeQuery("select balance from accounts where id = '"+depoacc+"' ");
	       
			}
			catch(Exception e)
			{
				System.out.println(e);
				e.printStackTrace();
			}
		}
		else
		{
			response.sendRedirect("404.html");
		}
		//changes
	}
}
