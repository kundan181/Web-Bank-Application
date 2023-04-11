
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectDB 
{
	static Connection con = null;
	public static Connection connect()
	{
		try
		{
			if(con == null)
			{
				Class.forName("com.mysql.jdbc.Driver");
				//System.out.println("Class loaded..");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webbankdb","root","");
				//System.out.println("Connection established.....");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return con;
	}
}
