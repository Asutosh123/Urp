package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseOperations {

	public DataBaseOperations() throws Exception{

	}

	public String readDataFromDataBase(String query) throws Exception {
		String re="";
		Connection con=null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			 con=DriverManager.getConnection("jdbc:mysql://172.25.10.54:3306/","forseuser","Forseqa@2018");   
			System.out.println(con);
			Statement stmt=con.createStatement();  
			ResultSet rs  = stmt.executeQuery(query);			
			System.out.println(rs);
			if(rs.next())  
				System.out.println(rs.getString(1)); 
			re=rs.getString(1);			
		}catch(Exception e){ System.out.println(e);}
		finally {
			if(null!=con)
			con.close();  
		}
		return re; 
	}  

}
