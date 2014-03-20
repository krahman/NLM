
public class SetupDatabaseClass {
	private String database = "";
	private String username = "";
	private String password = "";
	
	public SetupDatabaseClass() {		
		setDatabase("jdbc:mysql://127.0.0.1:3306/nlm");
		setUsername("root");
		setPassword("admin");
	}
	
	//public SetupDatabaseClass() {		
	//	setDatabase("jdbc:mysql://c1pher.s46.eatj.com:3307/c1pher");
	//	setUsername("c1pher");
	//	setPassword("Kholeel011279");
	//}
	
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getDatabase() {
		return database;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
}
