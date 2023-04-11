

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateAcc
 */
public class CreateAcc extends HttpServlet {
	String uname,mobno;
	int accno;
    int bal=0;
    int id;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAcc() {
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
		
	    String uname = request.getParameter("regname"); //regname = html page
	    String umobno = request.getParameter("regmobno");
		String ubal = request.getParameter("regbal");
		int rbal = Integer.parseInt(ubal);
		
		
		try
	    {
	       	Connection conn = ConnectDB.connect();
		   	PreparedStatement stmt1 = conn.prepareStatement("insert into accounts values(?,?,?,?)");
		   	stmt1.setInt(1, id);
		   	stmt1.setString(2,uname);		//upper dec var
		   	stmt1.setString(3,umobno);
		   	stmt1.setInt(4,rbal);
		   	
		   	int i = stmt1.executeUpdate();
		   	if(rbal>0)
		   	{
			   	if(i == 0)
			   	{
			   		response.sendRedirect("404.html");
			   	}
			   	else
			   	{
			   		response.sendRedirect("deposit.html");
			   	}
		   	}
		   	else{
		   		response.sendRedirect("404.html");
		   	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
			e.printStackTrace();
	    }
	}

}
