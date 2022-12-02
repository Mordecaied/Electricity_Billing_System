package ebs.database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conn {
	public Connection connection = null;
	//public PreparedStatement preparedStatement = null;
	//public String sql = "";
	
	private String driverClass = null;
	private String url = null;
	private String username = null;
	private String password = null;
	
	public Conn() {
		try {
			
			Properties prop = loadPropertiesFile();
			driverClass = prop.getProperty("MYSQLJDBC.driver");
			url = prop.getProperty("MYSQLJDBC.url");
			username = prop.getProperty("MYSQLJDBC.username");
			password = prop.getProperty("MYSQLJDBC.password");
			Class.forName(driverClass);
			
			connection = DriverManager.getConnection(url, username, password);
			
			
			if (connection != null) {
				System.out.println("connection created successfully using properties file");
			}

			else {
				System.out.println(" unable to create connection");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static Properties loadPropertiesFile() throws Exception {

		Properties prop = new Properties();
		InputStream in = new FileInputStream("config.properties");
		prop.load(in);
		in.close();
		return prop;
	}

}
