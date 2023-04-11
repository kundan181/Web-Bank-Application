<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "java.sql.*" %>
<%@ page import = "com.rcpit.data.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Account</title>
</head>
<style>
table, th, td {
  border: 1px solid red;
  border-collapse: collapse;
}
</style>
<body>
<h2 class="inner-tittle">Account Detail</h2>
			<div class="graph">
					<div class="tables">
							
							<table class="table">
								<thead>
									<tr>
									  <th>Account Number</th>&nbsp;
									  <th>Holder's Name</th>
									  <th>Mobile Number</th>
									  <th>Account Balance</th>
									</tr>
<%
					Connection con = ConnectDB.connect();
					try
					{
						PreparedStatement ps = con.prepareStatement("select * from accounts");
						ResultSet rs = ps.executeQuery();
						while(rs.next())
						{
%>										
							<tr>
								<td><%=rs.getInt("id")%></td>
								<td><%=rs.getString("name")%></td>
								<td><%=rs.getString("mobno")%></td>
								<td><%=rs.getInt("balance")%></td>
							</tr>	
<%						}	
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
%>
				</thead>
				
			</table>
		</div>

     </div>
</body>
</html>