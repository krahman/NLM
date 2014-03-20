/**
 * @author khalilur
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseClass extends SetupDatabaseClass{
	private Connection conn = null;	
	private int errNo =0;
	private String errDesc = null;	
	
	public DatabaseClass() {			
	}	
	
	public void closeConnection(){
		try {
			if (!conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Connection openConnection(){		
		try {			
			String className = "com.mysql.jdbc.Driver";			
			@SuppressWarnings("unused")
			Class driverObject = Class.forName(className);	
			try {
				conn = DriverManager.getConnection(getDatabase(),getUsername(),getPassword());
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(errNo);
				System.err.println(errDesc);
			}
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return conn;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isConnected(){
		boolean status = true;
		try {
			String className = "com.mysql.jdbc.Driver";		
			@SuppressWarnings("unused")
			Class driverObject = Class.forName(className);
			conn = DriverManager.getConnection(getDatabase(),getUsername(),getPassword());
		} catch (Exception e) {
			// TODO: handle exception
			status = false;
			System.err.println(errNo);
			System.err.println(errDesc);
		}
		return status;
	}
	public void setErrorNumber(int errorNumber){
		errNo = errorNumber;
	}
	public int getErrorNumber(){
		return errNo;
	}
	
	public void setErrorDescription(String errorDescription){
		errDesc = errorDescription;
	}
	public String getErrorDescription(){
		return errDesc;		
	}	
}
