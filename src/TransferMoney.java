

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TransferMoney
 */
public class TransferMoney extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferMoney() {
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
		
		int senderacno, reciveracno,transferAmt;
		double senderbal=0,reciverbal=0;
		
		try
    	{
    		
        	senderacno = Integer.parseInt(request.getParameter("senano"));
        	reciveracno = Integer.parseInt(request.getParameter("recano"));
    		Connection conn = ConnectDB.connect();
    		Statement stamt5 = conn.createStatement();
    		ResultSet rs = stamt5.executeQuery("select * from accounts where id = '"+senderacno +"'");
    	
    		while(rs.next())
    		{
    			senderbal=rs.getInt("balance");
    		}
      		ResultSet rs1 = stamt5.executeQuery("select * from accounts where id = '"+reciveracno +"'");
      		while(rs1.next())
    		{
    			reciverbal  =rs1.getInt("balance");
    		}
      		
      		
      		transferAmt = Integer.parseInt(request.getParameter("transamt"));  
      		
      		if(transferAmt>0)
      		{
		      		if( transferAmt > senderbal  )
		      		{
		      			response.sendRedirect("404.html");
		      		}
		      		else
		      		{
		      			senderbal = senderbal - transferAmt;
		      			int obj1 = stamt5.executeUpdate("update accounts set balance = '"+senderbal+"' where id = '"+senderacno+"'");
		      			if(obj1 == 0)
		      			{
		      				response.sendRedirect("404.html");
		      			}
		      			else
		      			{
		      				response.sendRedirect("transfermoney.html");
		      				//System.out.println("Money transfer successfully ...");
		      			}
		      			
		      			ResultSet rs2 = stamt5.executeQuery("select balance from accounts where id = '"+senderacno+"'");
		      			reciverbal = reciverbal+transferAmt;
		      			int obj2 = stamt5.executeUpdate("update accounts set balance = '"+reciverbal+"' where id = '"+reciveracno+"'");
		      }
      		}
      		else
      		{
      			response.sendRedirect("404.html");
      		}
    	}
    	catch(Exception e)
    	{
    		System.out.println("e");
    		e.printStackTrace();
    	}
		
	}

}
